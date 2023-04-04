import com.HuangTaiQi.www.po.ParkingLotEntity;
import com.HuangTaiQi.www.po.ParkingSpotEntity;
import com.HuangTaiQi.www.service.ParkService;
import com.HuangTaiQi.www.service.impl.ParkServiceImpl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class ParkServiceTest {
    ParkService parkService=new ParkServiceImpl();
    @Test
    public void testGetParkingLots() throws Exception {
        List<ParkingLotEntity> parkingLots = parkService.getParkingLots();
        assertNotNull(parkingLots);
    }
    @Test
    public void testGetParkingSpots() throws Exception {
        List<ParkingSpotEntity> parkingSpots = parkService.getParkingSpots(1);
        assertNotNull(parkingSpots);
    }
    @Test
    public void testAddParkingLot() throws SQLException, InterruptedException {
        parkService.addParkingLot("1","test");
    }
    @Test
    public void testAddParkingSpot() throws SQLException, InterruptedException {
        parkService.addParkingSpot(1);
    }
    @Test
    public void test() throws SQLException, InterruptedException {
        parkService.deleteSpot(3);
    }
}
