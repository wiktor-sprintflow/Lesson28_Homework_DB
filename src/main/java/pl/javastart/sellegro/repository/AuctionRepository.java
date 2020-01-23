package pl.javastart.sellegro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.javastart.sellegro.auction.Auction;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    @Transactional
    @Modifying
    @Query(value= "UPDATE Auction a SET a.title = :title WHERE a.id = :id")
    void updateAuctionTitle(@Param(value = "title") String title, @Param(value = "id") Long id);
//    void updateAuctionTitle(String title, Long id);

    List<Auction> findTop4ByOrderByPriceDesc();

    List<Auction> findAllByOrderByTitleAsc();
    List<Auction> findAllByOrderByPriceAsc();
    List<Auction> findAllByOrderByColorAsc();
    List<Auction> findAllByOrderByEndDateAsc();

    List <Auction> findByTitleContainingIgnoringCaseAndCarMakeContainingIgnoringCaseAndCarModelContainingIgnoringCaseAndColorContainingIgnoringCase(String title, String carMake, String carModel,String color);

}
