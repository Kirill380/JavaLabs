package workshop.task_2_2;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;

/**
 * Created by olenasyrota on 6/28/16.
 */
public class Exercise1SuppliersTest extends CompanyDomain {
    /**
     * entry point is company.
     * Solve this without changing the return type of {@link Company#getSuppliers()}.
     */
    @Test
    public void findSupplierNames() {
        List<String> supplierNames = stream(company.getSuppliers())
                .map(Supplier::getName)
                .collect(Collectors.toList());

        List<String> expectedSupplierNames = asList(
                "Shedtastic",
                "Splendid Crocks",
                "Annoying Pets",
                "Gnomes 'R' Us",
                "Furniture Hamlet",
                "SFD",
                "Doxins");
        Assert.assertEquals(expectedSupplierNames, supplierNames);
    }

    /**
     * entry point is company.
     * Create a {@link Predicate} for Suppliers that supply more than 2 items. Find the number of suppliers that
     * satisfy that Predicate.
     */
    @Test
    public void countSuppliersWithMoreThanTwoItems() {
        Predicate<Supplier> moreThanTwoItems = s -> s.getItemNames().length > 2;
        int suppliersWithMoreThanTwoItems = (int) stream(company.getSuppliers())
                .filter(moreThanTwoItems)
                .count();
        Assert.assertEquals("suppliers with more than 2 items", 5, suppliersWithMoreThanTwoItems);
    }

}