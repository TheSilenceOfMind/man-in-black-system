package com.itmo.mibsystem.model.distribute.technology;

import com.google.common.base.Objects;
import com.itmo.mibsystem.model.passporter.AlienRace;
import com.itmo.mibsystem.service.DistributeTechnologyService;
import com.itmo.mibsystem.service.PassporterService;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

@Getter
@Setter
public class BuyTechnologyMarket {
    private Long buyTechnologyMarketId;
    private String use;
    private Long count;
    private Long idRace;
    private Long idDeliveryType;
    private String description;

    private String raceName;
    private String technologyName;
    private String deliveryTypeName;
    private String paymentTypeName;

    final Random random = new Random();

    public BuyTechnologyMarket() {
        buyTechnologyMarketId = null;
        use = "";
        count = 0L;
        idRace = 0L;
        idDeliveryType = 0L;
        description = "";
    }

    public BuyTechnologyMarket(Long buyTechnologyMarketId, String use, Long count, Long idRace, Long idDeliveryType,String description) {
        this.buyTechnologyMarketId = buyTechnologyMarketId;
        this.use = use;
        this.count = count;
        this.idRace = idRace;
        this.idDeliveryType = idDeliveryType;
        this.description = description;
    }

    public BuyTechnologyMarket(Long buyTechnologyMarketId, PassporterService passporterService, DistributeTechnologyService distributeTechnologyService) {
        String[] alph = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
                "w", "x", "y", "z", " "};
        ArrayList<Long> buffId = new ArrayList<Long>();

        this.buyTechnologyMarketId = buyTechnologyMarketId;
        use = "";
        for (int i = 0; i < 5 + random.nextInt(10); i++) {
            use += alph[random.nextInt(27)];
        }

        count = (long) (random.nextInt(4) + 1);

        List<AlienRace> buffRace = passporterService.getAllRace();
        for(int i = 0; i < buffRace.size(); i++) {
            buffId.add(buffRace.get(i).getRaceId());
        }

        idRace = buffId.get(random.nextInt(buffId.size()));
        buffId.clear();

        List<DeliveryType> buffDeliveryType = distributeTechnologyService.getAllDeliveryType();
        for(int i = 0; i < buffDeliveryType.size(); i++) {
            buffId.add(buffDeliveryType.get(i).getDeliveryTypeId());
        }

        idDeliveryType = buffId.get(random.nextInt(buffId.size()));
        buffId.clear();
        technologyName = "";
        for (int i = 0; i < 5 + random.nextInt(10); i++) {
            technologyName += alph[random.nextInt(27)];
        }
        description = "";
        for (int i = 0; i < 5 + random.nextInt(10); i++) {
            description += alph[random.nextInt(27)];
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BuyTechnologyMarket document = (BuyTechnologyMarket) o;
        return Objects.equal(buyTechnologyMarketId, document.buyTechnologyMarketId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(buyTechnologyMarketId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BuyTechnologyMarket.class.getSimpleName() + "[", "]")
                .add("buyTechnologyDocumentId=" + buyTechnologyMarketId)
                .add("use=" + use)
                .add("count=" + count)
                .add("id_delivery_type=" + idDeliveryType)
                .add("description=" + description)
                .toString();
    }
}
