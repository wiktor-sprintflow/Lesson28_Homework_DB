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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AuctionService {

    private AuctionRepository auctionRepository;
    private List<Auction> auctions = new ArrayList<>();

    private static final String[] ADJECTIVES = {"Niesamowity", "Jedyny taki", "IGŁA", "HIT", "Jak nowy",
            "Perełka", "OKAZJA", "Wyjątkowy"};


    public AuctionService() {
    }

    @Autowired
    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
        this.auctions = auctionRepository.findAll();
        fillAuctionsTitle();

    }

    private void fillAuctionsTitle(){
        Random random = new Random();
        String randomAdjective;

        //chciałem to zrobić streamami, ale nie umiem. Musze sobie przypomnieć jak ich uzywac.
        for (Auction auction : auctions) {
            randomAdjective = ADJECTIVES[random.nextInt(ADJECTIVES.length)];
            auction.setTitle(randomAdjective + " " + auction.getCarMake() + " " + auction.getCarModel());
        }

    }

    public List<Auction> find4MostExpensive() {
        return auctions.stream()
                .sorted(Comparator.comparing(Auction::getPrice).reversed())
                .limit(4)
                .collect(Collectors.toList());
    }

    public List<Auction> findAllForFilters(AuctionFilters auctionFilters) {
        return auctions.stream()
                .filter(auction -> auctionFilters.getTitle() == null || auction.getTitle().toUpperCase().contains(auctionFilters.getTitle().toUpperCase()))
                .filter(auction -> auctionFilters.getCarMaker() == null || auction.getCarMake().toUpperCase().contains(auctionFilters.getCarMaker().toUpperCase()))
                .filter(auction -> auctionFilters.getCarModel() == null || auction.getCarModel().toUpperCase().contains(auctionFilters.getCarModel().toUpperCase()))
                .filter(auction -> auctionFilters.getColor() == null || auction.getColor().toUpperCase().contains(auctionFilters.getColor().toUpperCase()))
                .collect(Collectors.toList());
    }

    public List<Auction> findAllSorted(String sort) {
        Comparator<Auction> comparator = Comparator.comparing(Auction::getTitle);
        if (sort.equals("title")) {
            comparator = Comparator.comparing(Auction::getTitle);
        } else if (sort.equals("price")) {
            comparator = Comparator.comparing(Auction::getPrice);
        } else if (sort.equals("color")) {
            comparator = Comparator.comparing(Auction::getColor);
        } else if (sort.equals("endDate")) {
            comparator = Comparator.comparing(Auction::getEndDate);
        }

            return auctions.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        }
    }
