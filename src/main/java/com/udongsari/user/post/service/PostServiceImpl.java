package com.udongsari.user.post.service;

import com.udongsari.account.entity.Account;
import com.udongsari.exception.PostNotFoundException;
import com.udongsari.grapher.region.dto.RegionDto;
import com.udongsari.grapher.region.entity.Region;
import com.udongsari.grapher.region.repository.RegionRepository;
import com.udongsari.grapher.thema.dto.ThemaDto;
import com.udongsari.grapher.thema.entity.Thema;
import com.udongsari.grapher.thema.repository.ThemaRepository;
import com.udongsari.user.portfolio.dto.UserPortfolioDto;
import com.udongsari.user.portfolio.entity.UserPortfolio;
import com.udongsari.user.portfolio.repository.UserPortfolioRepository;
import com.udongsari.user.post.dto.UserPostDto;
import com.udongsari.user.post.entity.UserPost;
import com.udongsari.user.post.model.UserPostRepository;
import com.udongsari.user.user_region.entity.User_Region;
import com.udongsari.user.user_region.repository.User_RegionRepository;
import com.udongsari.user.user_thema.entity.UserThema;
import com.udongsari.user.user_thema.repository.UserThemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final UserPostRepository userPostRepository;
    private final User_RegionRepository user_regionRepository;
    private final RegionRepository regionRepository;
    private final UserThemaRepository user_themaRepository;
    private final UserPortfolioRepository userPortfolioRepository;
    private final ThemaRepository themaRepository;

    @Override
    public Long createPost(Account account, UserPostDto userPostDto){

        // post 저장
        UserPost userPost = userPostRepository.save(UserPost.builder()
                .account(account)
                .intro(userPostDto.getIntro())
                .startDate(userPostDto.getStartDate())
                .endDate(userPostDto.getEndDate())
                .price(userPostDto.getPrice()).build());


        List<UserPortfolioDto> userPortfolioDtoList = userPostDto.getPortfolioList();

        // portfolio 저장
        for (UserPortfolioDto userPortfolioDto : userPortfolioDtoList){
            userPortfolioRepository.save(UserPortfolio.builder()
                    .userPost(userPost)
                    .imageTitle(userPortfolioDto.getImageTitle())
                    .imagePath(userPortfolioDto.getImagePath())
                    .build());
        }


        // thema 저장
        List<ThemaDto> themaDtos = userPostDto.getThemaList();

        for (ThemaDto themaDto : themaDtos) {
            Optional<Thema> themaOptional = themaRepository.findByThemaName(themaDto.getThemaName());

            if (themaOptional.isEmpty()) {
                Thema thema = themaRepository.save(Thema.builder()
                        .themaName(themaDto.getThemaName())
                        .build());

                user_themaRepository.save(UserThema.builder()
                        .userPost(userPost)
                        .thema(thema)
                        .build());
            } else {
                user_themaRepository.save(UserThema.builder()
                        .userPost(userPost)
                        .thema(themaOptional.get())
                        .build());
            }
        }

        // region 저장
        List<RegionDto> regionDtos = userPostDto.getRegionList();

        for (RegionDto regionDto : regionDtos) {
            Optional<Region> regionOptional = regionRepository.findBySiAndGuAndDong(regionDto.getSi(), regionDto.getGu(), regionDto.getDong());

            if (regionOptional.isEmpty()) {
                Region region = regionRepository.save(Region.builder()
                        .si(regionDto.getSi())
                        .gu(regionDto.getGu())
                        .dong(regionDto.getDong())
                        .build());

                user_regionRepository.save(User_Region.builder()
                        .region(region)
                        .userPost(userPost)
                        .build());
            } else {
                user_regionRepository.save(User_Region.builder()
                        .region(regionOptional.get())
                        .userPost(userPost)
                        .build());
            }
        }

        return userPost.getId();
    }

    @Override
    public UserPost showMyPost(Account account){
        return account.getUserPost();

    }

    @Override
    public List<UserPostDto> searchUsersPost(String si, String gu, String dong){
        List<UserPostDto> userPostDtos = new ArrayList<>();
        List<Region> regions = new ArrayList<>();

        if (si != null && gu != null && dong != null) { // si, gu, dong 있을 때
            regions = regionRepository.findAllBySiAndGuAndDong(si, gu, dong);
        } else if (si != null && gu != null) { // si, gu 있을 때
            regions = regionRepository.findAllBySiAndGu(si, gu);
        } else if (si != null) { // si 있을 때
            regions = regionRepository.findAllBySi(si);
        }

        for (Region region : regions) {
            List<User_Region> userRegions = region.getUserRegions();

            for (User_Region user_region : userRegions) {
                UserPost userPost = user_region.getUserPost();

                userPostDtos.add(UserPostDto.builder()
                        .id(userPost.getId())
                        .account_id(userPost.getAccount().getId())
                        .userName(userPost.getAccount().getName())
                        .intro(userPost.getIntro())
                        .price(userPost.getPrice())
                        .startDate(userPost.getStartDate())
                        .endDate(userPost.getEndDate())
                        .themaList(userPost.getThemaDtoList())
                        .portfolioList(userPost.getPortfolioDtoList())
                        .regionList(userPost.getRegionDtoList())
                        .build());
            }
        }

        return userPostDtos;
    }

    @Override
    public UserPostDto showUserPost(Long post_id) {
        Optional<UserPost> userPostOptional = userPostRepository.findById(post_id);

        if (userPostOptional.isEmpty()) {
            throw new PostNotFoundException(" * 포스트를 찾을 수 없음.");
        }

        UserPost userPost = userPostOptional.get();

        return userPost.toDto();
    }
}
