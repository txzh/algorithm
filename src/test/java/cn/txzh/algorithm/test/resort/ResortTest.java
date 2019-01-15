package cn.txzh.algorithm.test.resort;

import cn.txzh.resort.Resort;
import cn.txzh.resort.ResortElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leizhao_3 on 2019/1/7.
 */
public class ResortTest {

    public static void main(String[] args) {
        List<House> houses = new ArrayList<House>();
        House house1 = new House();
        house1.setHouseId(1);
        house1.setType(1);
        house1.setHouseName("房屋1");
        houses.add(house1);

        House house2 = new House();
        house2.setHouseId(2);
        house2.setType(1);
        house2.setHouseName("房屋2");
        houses.add(house2);

        House house3 = new House();
        house3.setHouseId(3);
        house3.setType(1);
        house3.setHouseName("房屋3");
        houses.add(house3);

        House house4 = new House();
        house4.setHouseId(4);
        house4.setType(2);
        house4.setHouseName("房屋4");
        houses.add(house4);

        House house5 = new House();
        house5.setHouseId(5);
        house5.setType(3);
        house5.setHouseName("房屋5");
        houses.add(house5);

        House house6 = new House();
        house6.setHouseId(6);
        house6.setType(4);
        house6.setHouseName("房屋6");
        houses.add(house6);

        House house7 = new House();
        house7.setHouseId(7);
        house7.setType(5);
        house7.setHouseName("房屋7");
        houses.add(house7);

        House house8 = new House();
        house8.setHouseId(8);
        house8.setType(9);
        house8.setHouseName("房屋8");
        houses.add(house8);

        House house9 = new House();
        house9.setHouseId(9);
        house9.setType(5);
        house9.setHouseName("房屋9");
        houses.add(house9);

        House house10 = new House();
        house10.setHouseId(10);
        house10.setType(2);
        house10.setHouseName("房屋10");
        houses.add(house10);

        House house11 = new House();
        house11.setHouseId(11);
        house11.setType(5);
        house11.setHouseName("房屋11");
        houses.add(house11);

        House house12 = new House();
        house12.setHouseId(12);
        house12.setType(12);
        house12.setHouseName("房屋12");
        houses.add(house12);

        System.out.println("----- 重排序之前 -------");
        for (House house : houses) {
            System.out.println(house);
        }

       /* List<ResortElement> resortElements = new ArrayList<ResortElement>();
        resortElements.addAll(houses);
        Resort.reSort(resortElements, 3);
        System.out.println("----- 重排序之后 -------");

        for (ResortElement resortElement : resortElements) {
            System.out.println(resortElement);
        }*/

        List<ResortElement> resortElements = new ArrayList<ResortElement>();
        resortElements.addAll(houses);
        Resort.reSort2(resortElements, 3, true);
        System.out.println("----- 重排序之后 -------");

        for (ResortElement resortElement : resortElements) {
            System.out.println(resortElement);
        }
    }
}
