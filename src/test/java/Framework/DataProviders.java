package Framework;

import org.testng.annotations.DataProvider;

/**
 * Created by mafb on 14.11.2018.
 */
public class DataProviders {
    @DataProvider(name="smokeProvider")
    public Object[][] createData (){
        return new Object[][]{
                {"Mirek",20},
                {"Monia",25}
        };
    }
}
