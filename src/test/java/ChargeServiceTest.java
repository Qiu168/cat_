import com.HuangTaiQi.www.po.ChargingPileEntity;
import com.HuangTaiQi.www.po.ChargingStationEntity;
import com.HuangTaiQi.www.service.ChargeService;
import com.HuangTaiQi.www.service.impl.ChargeServiceImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class ChargeServiceTest {
    ChargeService chargeService=new ChargeServiceImpl();
    @Test
    public void testGetChargingPilesByStationId() throws Exception {
        List<ChargingPileEntity> chargingPilesByStationId = chargeService.getChargingPilesByStationId(1);
        assertNotNull(chargingPilesByStationId);
    }
    @Test
    public void testGetChargingStations() throws Exception {
        List<ChargingStationEntity> chargingStations = chargeService.getChargingStations();
        assertNotNull(chargingStations);
    }



}
