package cn.txzh.algorithm.test.resort;

import cn.txzh.resort.ResortElement;

/**
 * Created by leizhao_3 on 2019/1/7.
 */
public class House implements ResortElement{
    private long houseId;
    private int type;
    private String houseName;

    public long getHouseId() {
        return houseId;
    }

    public void setHouseId(long houseId) {
        this.houseId = houseId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public boolean similar(ResortElement resortElement) {
        return this.type == ((House)resortElement).getType();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("House{");
        sb.append("houseId=").append(houseId);
        sb.append(", type=").append(type);
        sb.append(", houseName='").append(houseName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
