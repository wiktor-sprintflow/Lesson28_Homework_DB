package pl.javastart.sellegro.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.comparator.Comparators;
import pl.javastart.sellegro.repository.AuctionRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuctionService {

    private AuctionRepository auctionRepository;

    public AuctionService() {
    }

    @Autowired
    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }


    public List<Auction> find4MostExpensive() {
        return auctionRepository.findTop4ByOrderByPriceDesc();
    }

    public List<Auction> findAllForFilters(AuctionFilters auctionFilters) {

        Optional<String> optionalTitle = Optional.ofNullable(auctionFilters.getTitle());
        Optional<String> optionalCarMake = Optional.ofNullable(auctionFilters.getCarMaker());
        Optional<String> optionalCarModel = Optional.ofNullable(auctionFilters.getCarModel());
        Optional<String> optionalColor = Optional.ofNullable(auctionFilters.getColor());

        String title = optionalTitle.orElse("");
        String carMake = optionalCarMake.orElse("");
        String carModel = optionalCarModel.orElse("");
        String color = optionalColor.orElse("");

        return auctionRepository.
                findByTitleContainingIgnoringCaseAndCarMakeContainingIgnoringCaseAndCarModelContainingIgnoringCaseAndColorContainingIgnoringCase(title, carMake, carModel, color);

    }

    public List<Auction> findAllSorted(String sort) {

        List<Auction> orderBy;

        switch (sort) {
            case "title":
                orderBy = auctionRepository.findAllByOrderByTitleAsc();
                break;
            case "price":
                orderBy = auctionRepository.findAllByOrderByPriceAsc();
                break;
            case "color":
                orderBy = auctionRepository.findAllByOrderByColorAsc();
                break;
            case "endDate":
                orderBy = auctionRepository.findAllByOrderByEndDateAsc();
                break;
            default:
                orderBy = auctionRepository.findAllByOrderByTitleAsc();
        }
        return orderBy;
    }
}
