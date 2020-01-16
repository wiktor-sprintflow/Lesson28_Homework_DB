package pl.javastart.sellegro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javastart.sellegro.auction.Auction;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

}
