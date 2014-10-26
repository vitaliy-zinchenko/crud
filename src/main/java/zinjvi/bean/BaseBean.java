package zinjvi.bean;

/**
 * Created by zinchenko on 24.10.14.
 */
public class BaseBean<I> implements IdShower<I> {

    protected I id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseBean baseBean = (BaseBean) o;

        if (id != null ? !id.equals(baseBean.id) : baseBean.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public I getId() {
        return id;
    }

    public void setId(I id) {
        this.id = id;
    }
}
