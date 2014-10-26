package zinjvi;

import zinjvi.bean.BaseBean;

/**
 * Created by zinchenko on 23.10.14.
 */
public class Bean extends BaseBean<String> {

    private String name;

    @Override
    public String toString() {
        return "Bean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
