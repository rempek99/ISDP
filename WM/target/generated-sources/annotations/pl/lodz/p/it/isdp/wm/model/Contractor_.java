package pl.lodz.p.it.isdp.wm.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pl.lodz.p.it.isdp.wm.model.OfficeAccount;
import pl.lodz.p.it.isdp.wm.model.Stock;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-14T22:31:05")
@StaticMetamodel(Contractor.class)
public class Contractor_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Contractor, String> zip;
    public static volatile SingularAttribute<Contractor, OfficeAccount> modificatedBy;
    public static volatile SingularAttribute<Contractor, String> city;
    public static volatile SingularAttribute<Contractor, String> regon;
    public static volatile SingularAttribute<Contractor, String> contractorName;
    public static volatile SingularAttribute<Contractor, Boolean> active;
    public static volatile SingularAttribute<Contractor, String> house;
    public static volatile ListAttribute<Contractor, Stock> stocks;
    public static volatile SingularAttribute<Contractor, String> contractorNumber;
    public static volatile SingularAttribute<Contractor, String> nip;
    public static volatile SingularAttribute<Contractor, OfficeAccount> createdBy;
    public static volatile SingularAttribute<Contractor, String> street;
    public static volatile SingularAttribute<Contractor, Long> id;
    public static volatile SingularAttribute<Contractor, String> apartment;

}