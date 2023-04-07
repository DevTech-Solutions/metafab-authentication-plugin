package us.devtechsolutions.metafab.bukkit.item;

/**
 * @author LBuke (Teddeh)
 */
public abstract class Builder<T> {

    protected T object = null;

    public Builder(T object) {
        this.object = object;
    }

    public abstract T build();
}
