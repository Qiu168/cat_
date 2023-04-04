import com.HuangTaiQi.www.po.ParkingLotEntity;
import com.HuangTaiQi.www.po.ParkingSpotEntity;
import com.HuangTaiQi.www.service.ParkService;
import com.HuangTaiQi.www.service.impl.ParkServiceImpl;
import org.junit.Test;

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
}
