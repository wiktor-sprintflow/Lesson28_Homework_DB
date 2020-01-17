package pl.javastart.sellegro.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.javastart.sellegro.auction.Auction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer {

    private static final String[] ADJECTIVES = {"Niesamowity", "Jedyny taki", "IGŁA", "HIT", "Jak nowy",
            "Perełka", "OKAZJA", "Wyjątkowy"};

    private AuctionRepository auctionRepository;

    public DataInitializer() {
    }

    @Autowired
    public DataInitializer(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
        updateAuctionsTitles();
    }

    private void updateAuctionsTitles() {
        Random random = new Random();
        String randomAdjective;
        String title;

        List<Auction> auctionsFromDb = auctionRepository.findAll();

        //chciałem to zrobić streamami, ale nie umiem. Musze sobie przypomnieć jak ich uzywac.
        for (Auction auction : auctionsFromDb) {
            randomAdjective = ADJECTIVES[random.nextInt(ADJECTIVES.length)];
            title = (randomAdjective + " " + auction.getCarMake() + " " + auction.getCarModel());
            auctionRepository.updateAuctionTitle(title, auction.getId());
        }
    }

    public AuctionRepository getAuctionRepository() {
        return auctionRepository;
    }

    public void setAuctionRepository(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }
}
