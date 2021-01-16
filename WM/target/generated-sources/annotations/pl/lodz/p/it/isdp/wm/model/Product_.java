package pl.lodz.p.it.isdp.wm.model;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pl.lodz.p.it.isdp.wm.model.OfficeAccount;
import pl.lodz.p.it.isdp.wm.model.Stock;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-14T22:31:05")
@StaticMetamodel(Product.class)
public class Product_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Product, String> productSymbol;
    public static volatile SingularAttribute<Product, OfficeAccount> modificatedBy;
    public static volatile SingularAttribute<Product, OfficeAccount> createdBy;
    public static volatile SingularAttribute<Product, BigDecimal> price;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SingularAttribute<Product, Integer> weight;
    public static volatile SingularAttribute<Product, Long> id;
    public static volatile ListAttribute<Product, Stock> stocks;
    public static volatile SingularAttribute<Product, Boolean> easilyDamage;

}