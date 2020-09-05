package fooqoo.trade.stock.crawler.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 業種
 */
@RequiredArgsConstructor
@Getter
public enum Sector {
  SERVICES("サービス業"),
  INFORMATION_AND_COMMUNICATION("情報・通信"),
  RETAIL("小売業"),
  WHOLESALE("卸売業"),
  ELECTRIC_APPLIANCES("電気機器"),
  MACHINERY("機械"),
  CHEMICALS("化学"),
  CONSTRUCTION("建設業"),
  REAL_ESTATE("不動産業"),
  FOODS("食料品"),
  OTHER_PRODUCTS("その他製品"),
  METAL_PRODUCTS("金属製品"),
  AUTOMOTIVE("輸送用機器"),
  BANKS("銀行業"),
  PHARMACEUTICALS("医薬品"),
  LAND_TRANSPORTATION("陸運業"),
  REIT("REIT銘柄一覧"),
  GRASS_AND_CERAMICS_PRODUCTS("ガラス・土石製品"),
  TEXTILES_AND_APPARELS("繊維製品"),
  PRECISION_INSTRUMENTS("精密機器"),
  IRON_AND_STEEL("鉄鋼"),
  SECURITIES("証券業"),
  WAREHOUSING_AND_HARBOR_TRANSPORTATION("倉庫・運輸関連業"),
  OTHER_FINANCING_BUSINESS("その他金融業"),
  NONFERROUS_METALS("非鉄金属"),
  PULP_AND_PAPER("パルプ・紙"),
  ELECTRIC_POWER_AND_GAS("電気・ガス業"),
  RUBBER_PRODUCTS("ゴム製品"),
  INSURANCE("保険業"),
  MARINE_TRANSPORTATION("海運業"),
  FISHERY_AGRICULTURE_AND_FORESTRY("水産・農林業"),
  OIL_AND_COAL_PRODUCTS("石油・石炭製品"),
  MINING("鉱業"),
  AIR_TRANSPORTATION("空運業");

  private final String name;
}
