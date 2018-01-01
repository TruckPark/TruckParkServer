package de.htwg.moco.truckparkserver;

import de.htwg.moco.truckparkserver.service.FirestoreService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DirectionPathTest {

    @Resource
    FirestoreService firestoreService;

    @Before
    public void setup(){
        firestoreService = new FirestoreService();
    }

    @Test
    public void whenPostPathAsJson_thenReturnParkingLotsNearPath() throws Exception {
        String pathAsJson = "{\"230\":\"{\\\"lat\\\":48.73046,\\\"lng\\\":9.088320000000001}\",\"110\":\"{\\\"lat\\\":48.09646,\\\"lng\\\":8.589970000000001}\",\"231\":\"{\\\"lat\\\":48.73248,\\\"lng\\\":9.091460000000001}\",\"111\":\"{\\\"lat\\\":48.10345,\\\"lng\\\":8.58558}\",\"232\":\"{\\\"lat\\\":48.735330000000005,\\\"lng\\\":9.0937}\",\"112\":\"{\\\"lat\\\":48.11681,\\\"lng\\\":8.58036}\",\"233\":\"{\\\"lat\\\":48.737100000000005,\\\"lng\\\":9.09651}\",\"113\":\"{\\\"lat\\\":48.127410000000005,\\\"lng\\\":8.57407}\",\"234\":\"{\\\"lat\\\":48.739670000000004,\\\"lng\\\":9.106330000000002}\",\"114\":\"{\\\"lat\\\":48.13541000000001,\\\"lng\\\":8.57116}\",\"235\":\"{\\\"lat\\\":48.740260000000006,\\\"lng\\\":9.10961}\",\"115\":\"{\\\"lat\\\":48.139770000000006,\\\"lng\\\":8.57074}\",\"236\":\"{\\\"lat\\\":48.741730000000004,\\\"lng\\\":9.110660000000001}\",\"116\":\"{\\\"lat\\\":48.14537000000001,\\\"lng\\\":8.571330000000001}\",\"237\":\"{\\\"lat\\\":48.74428,\\\"lng\\\":9.111550000000001}\",\"117\":\"{\\\"lat\\\":48.153670000000005,\\\"lng\\\":8.573070000000001}\",\"238\":\"{\\\"lat\\\":48.74658,\\\"lng\\\":9.113180000000002}\",\"118\":\"{\\\"lat\\\":48.16642,\\\"lng\\\":8.57238}\",\"239\":\"{\\\"lat\\\":48.748990000000006,\\\"lng\\\":9.115250000000001}\",\"119\":\"{\\\"lat\\\":48.17334,\\\"lng\\\":8.57325}\",\"10\":\"{\\\"lat\\\":47.67434,\\\"lng\\\":9.15492}\",\"11\":\"{\\\"lat\\\":47.677760000000006,\\\"lng\\\":9.147820000000001}\",\"12\":\"{\\\"lat\\\":47.68139,\\\"lng\\\":9.136230000000001}\",\"13\":\"{\\\"lat\\\":47.685790000000004,\\\"lng\\\":9.12648}\",\"14\":\"{\\\"lat\\\":47.68845,\\\"lng\\\":9.12105}\",\"15\":\"{\\\"lat\\\":47.691140000000004,\\\"lng\\\":9.116810000000001}\",\"16\":\"{\\\"lat\\\":47.69708000000001,\\\"lng\\\":9.11327}\",\"17\":\"{\\\"lat\\\":47.70026000000001,\\\"lng\\\":9.113180000000002}\",\"18\":\"{\\\"lat\\\":47.70065,\\\"lng\\\":9.11332}\",\"19\":\"{\\\"lat\\\":47.70161,\\\"lng\\\":9.11214}\",\"240\":\"{\\\"lat\\\":48.75206000000001,\\\"lng\\\":9.11579}\",\"120\":\"{\\\"lat\\\":48.180040000000005,\\\"lng\\\":8.5761}\",\"241\":\"{\\\"lat\\\":48.755680000000005,\\\"lng\\\":9.1159}\",\"0\":\"{\\\"lat\\\":47.66799,\\\"lng\\\":9.16938}\",\"121\":\"{\\\"lat\\\":48.18558,\\\"lng\\\":8.58022}\",\"242\":\"{\\\"lat\\\":48.75766,\\\"lng\\\":9.11686}\",\"1\":\"{\\\"lat\\\":47.66707,\\\"lng\\\":9.168280000000001}\",\"122\":\"{\\\"lat\\\":48.190810000000006,\\\"lng\\\":8.58601}\",\"243\":\"{\\\"lat\\\":48.75862,\\\"lng\\\":9.12058}\",\"2\":\"{\\\"lat\\\":47.66727,\\\"lng\\\":9.166690000000001}\",\"123\":\"{\\\"lat\\\":48.195820000000005,\\\"lng\\\":8.59425}\",\"244\":\"{\\\"lat\\\":48.758230000000005,\\\"lng\\\":9.128060000000001}\",\"3\":\"{\\\"lat\\\":47.667530000000006,\\\"lng\\\":9.1644}\",\"124\":\"{\\\"lat\\\":48.198780000000006,\\\"lng\\\":8.60139}\",\"245\":\"{\\\"lat\\\":48.75723000000001,\\\"lng\\\":9.131450000000001}\",\"4\":\"{\\\"lat\\\":47.66738,\\\"lng\\\":9.16408}\",\"125\":\"{\\\"lat\\\":48.20590000000001,\\\"lng\\\":8.623930000000001}\",\"246\":\"{\\\"lat\\\":48.756440000000005,\\\"lng\\\":9.135940000000002}\",\"5\":\"{\\\"lat\\\":47.66646,\\\"lng\\\":9.16361}\",\"126\":\"{\\\"lat\\\":48.211580000000005,\\\"lng\\\":8.635140000000002}\",\"247\":\"{\\\"lat\\\":48.75433,\\\"lng\\\":9.139560000000001}\",\"6\":\"{\\\"lat\\\":47.666650000000004,\\\"lng\\\":9.162410000000001}\",\"127\":\"{\\\"lat\\\":48.21842,\\\"lng\\\":8.643690000000001}\",\"248\":\"{\\\"lat\\\":48.754470000000005,\\\"lng\\\":9.145380000000001}\",\"7\":\"{\\\"lat\\\":47.67233,\\\"lng\\\":9.16337}\",\"128\":\"{\\\"lat\\\":48.222460000000005,\\\"lng\\\":8.647210000000001}\",\"249\":\"{\\\"lat\\\":48.755160000000004,\\\"lng\\\":9.15755}\",\"8\":\"{\\\"lat\\\":47.67329,\\\"lng\\\":9.16234}\",\"129\":\"{\\\"lat\\\":48.22614,\\\"lng\\\":8.64929}\",\"9\":\"{\\\"lat\\\":47.67333000000001,\\\"lng\\\":9.15938}\",\"20\":\"{\\\"lat\\\":47.703970000000005,\\\"lng\\\":9.106670000000001}\",\"21\":\"{\\\"lat\\\":47.70709,\\\"lng\\\":9.10065}\",\"22\":\"{\\\"lat\\\":47.709050000000005,\\\"lng\\\":9.096580000000001}\",\"23\":\"{\\\"lat\\\":47.709140000000005,\\\"lng\\\":9.09326}\",\"24\":\"{\\\"lat\\\":47.71114000000001,\\\"lng\\\":9.085780000000002}\",\"25\":\"{\\\"lat\\\":47.713550000000005,\\\"lng\\\":9.080110000000001}\",\"26\":\"{\\\"lat\\\":47.71647,\\\"lng\\\":9.07586}\",\"27\":\"{\\\"lat\\\":47.720890000000004,\\\"lng\\\":9.072700000000001}\",\"28\":\"{\\\"lat\\\":47.723150000000004,\\\"lng\\\":9.069550000000001}\",\"29\":\"{\\\"lat\\\":47.72449,\\\"lng\\\":9.063960000000002}\",\"250\":\"{\\\"lat\\\":48.75583,\\\"lng\\\":9.160870000000001}\",\"130\":\"{\\\"lat\\\":48.230790000000006,\\\"lng\\\":8.650580000000001}\",\"251\":\"{\\\"lat\\\":48.7571,\\\"lng\\\":9.16441}\",\"131\":\"{\\\"lat\\\":48.236360000000005,\\\"lng\\\":8.65023}\",\"252\":\"{\\\"lat\\\":48.759820000000005,\\\"lng\\\":9.1681}\",\"132\":\"{\\\"lat\\\":48.24403,\\\"lng\\\":8.64747}\",\"253\":\"{\\\"lat\\\":48.763040000000004,\\\"lng\\\":9.169820000000001}\",\"133\":\"{\\\"lat\\\":48.26599,\\\"lng\\\":8.63882}\",\"254\":\"{\\\"lat\\\":48.764630000000004,\\\"lng\\\":9.16956}\",\"134\":\"{\\\"lat\\\":48.27116,\\\"lng\\\":8.63856}\",\"255\":\"{\\\"lat\\\":48.76863,\\\"lng\\\":9.172870000000001}\",\"135\":\"{\\\"lat\\\":48.27747,\\\"lng\\\":8.640260000000001}\",\"256\":\"{\\\"lat\\\":48.771060000000006,\\\"lng\\\":9.17703}\",\"136\":\"{\\\"lat\\\":48.28369000000001,\\\"lng\\\":8.64104}\",\"257\":\"{\\\"lat\\\":48.77573,\\\"lng\\\":9.183060000000001}\",\"137\":\"{\\\"lat\\\":48.29064,\\\"lng\\\":8.63738}\",\"258\":\"{\\\"lat\\\":48.776880000000006,\\\"lng\\\":9.182}\",\"138\":\"{\\\"lat\\\":48.29737,\\\"lng\\\":8.63507}\",\"259\":\"{\\\"lat\\\":48.77767,\\\"lng\\\":9.17952}\",\"139\":\"{\\\"lat\\\":48.30395000000001,\\\"lng\\\":8.635580000000001}\",\"30\":\"{\\\"lat\\\":47.72524000000001,\\\"lng\\\":9.054170000000001}\",\"31\":\"{\\\"lat\\\":47.727520000000005,\\\"lng\\\":9.04141}\",\"32\":\"{\\\"lat\\\":47.729780000000005,\\\"lng\\\":9.029710000000001}\",\"33\":\"{\\\"lat\\\":47.73241,\\\"lng\\\":9.02992}\",\"34\":\"{\\\"lat\\\":47.73418,\\\"lng\\\":9.024930000000001}\",\"35\":\"{\\\"lat\\\":47.73892000000001,\\\"lng\\\":9.018820000000002}\",\"36\":\"{\\\"lat\\\":47.744640000000004,\\\"lng\\\":9.013940000000002}\",\"37\":\"{\\\"lat\\\":47.748290000000004,\\\"lng\\\":9.00937}\",\"38\":\"{\\\"lat\\\":47.751780000000004,\\\"lng\\\":9.002270000000001}\",\"39\":\"{\\\"lat\\\":47.755750000000006,\\\"lng\\\":8.995410000000001}\",\"260\":\"{\\\"lat\\\":48.7792,\\\"lng\\\":9.17642}\",\"140\":\"{\\\"lat\\\":48.30819,\\\"lng\\\":8.63761}\",\"261\":\"{\\\"lat\\\":48.77969,\\\"lng\\\":9.174930000000002}\",\"141\":\"{\\\"lat\\\":48.311260000000004,\\\"lng\\\":8.64066}\",\"262\":\"{\\\"lat\\\":48.779860000000006,\\\"lng\\\":9.175640000000001}\",\"142\":\"{\\\"lat\\\":48.315110000000004,\\\"lng\\\":8.6448}\",\"263\":\"{\\\"lat\\\":48.78089000000001,\\\"lng\\\":9.17563}\",\"143\":\"{\\\"lat\\\":48.317620000000005,\\\"lng\\\":8.646410000000001}\",\"264\":\"{\\\"lat\\\":48.78119,\\\"lng\\\":9.17692}\",\"144\":\"{\\\"lat\\\":48.322720000000004,\\\"lng\\\":8.647630000000001}\",\"265\":\"{\\\"lat\\\":48.778800000000004,\\\"lng\\\":9.175680000000002}\",\"145\":\"{\\\"lat\\\":48.32838,\\\"lng\\\":8.646700000000001}\",\"266\":\"{\\\"lat\\\":48.77928000000001,\\\"lng\\\":9.1744}\",\"146\":\"{\\\"lat\\\":48.333560000000006,\\\"lng\\\":8.646920000000001}\",\"267\":\"{\\\"lat\\\":48.778740000000006,\\\"lng\\\":9.17669}\",\"147\":\"{\\\"lat\\\":48.33836,\\\"lng\\\":8.6493}\",\"268\":\"{\\\"lat\\\":48.778090000000006,\\\"lng\\\":9.177890000000001}\",\"148\":\"{\\\"lat\\\":48.34364,\\\"lng\\\":8.6552}\",\"149\":\"{\\\"lat\\\":48.34689,\\\"lng\\\":8.66135}\",\"40\":\"{\\\"lat\\\":47.76089,\\\"lng\\\":8.988520000000001}\",\"41\":\"{\\\"lat\\\":47.76525,\\\"lng\\\":8.980690000000001}\",\"42\":\"{\\\"lat\\\":47.76771,\\\"lng\\\":8.974450000000001}\",\"43\":\"{\\\"lat\\\":47.771150000000006,\\\"lng\\\":8.95902}\",\"44\":\"{\\\"lat\\\":47.77132,\\\"lng\\\":8.9411}\",\"45\":\"{\\\"lat\\\":47.769450000000006,\\\"lng\\\":8.927850000000001}\",\"46\":\"{\\\"lat\\\":47.769650000000006,\\\"lng\\\":8.91783}\",\"47\":\"{\\\"lat\\\":47.77156,\\\"lng\\\":8.9107}\",\"48\":\"{\\\"lat\\\":47.775310000000005,\\\"lng\\\":8.904050000000002}\",\"49\":\"{\\\"lat\\\":47.787200000000006,\\\"lng\\\":8.890600000000001}\",\"150\":\"{\\\"lat\\\":48.350100000000005,\\\"lng\\\":8.66843}\",\"151\":\"{\\\"lat\\\":48.355850000000004,\\\"lng\\\":8.67828}\",\"152\":\"{\\\"lat\\\":48.359370000000006,\\\"lng\\\":8.6826}\",\"153\":\"{\\\"lat\\\":48.366130000000005,\\\"lng\\\":8.68895}\",\"154\":\"{\\\"lat\\\":48.370850000000004,\\\"lng\\\":8.69378}\",\"155\":\"{\\\"lat\\\":48.37375,\\\"lng\\\":8.6982}\",\"156\":\"{\\\"lat\\\":48.37583,\\\"lng\\\":8.703320000000001}\",\"157\":\"{\\\"lat\\\":48.37742,\\\"lng\\\":8.709710000000001}\",\"158\":\"{\\\"lat\\\":48.37921,\\\"lng\\\":8.71598}\",\"159\":\"{\\\"lat\\\":48.382630000000006,\\\"lng\\\":8.722370000000002}\",\"50\":\"{\\\"lat\\\":47.791030000000006,\\\"lng\\\":8.88367}\",\"51\":\"{\\\"lat\\\":47.795320000000004,\\\"lng\\\":8.87156}\",\"52\":\"{\\\"lat\\\":47.798700000000004,\\\"lng\\\":8.865530000000001}\",\"53\":\"{\\\"lat\\\":47.807140000000004,\\\"lng\\\":8.85711}\",\"54\":\"{\\\"lat\\\":47.81264,\\\"lng\\\":8.853570000000001}\",\"55\":\"{\\\"lat\\\":47.81602,\\\"lng\\\":8.84889}\",\"56\":\"{\\\"lat\\\":47.822500000000005,\\\"lng\\\":8.83773}\",\"57\":\"{\\\"lat\\\":47.8325,\\\"lng\\\":8.82287}\",\"58\":\"{\\\"lat\\\":47.849000000000004,\\\"lng\\\":8.80751}\",\"59\":\"{\\\"lat\\\":47.86242000000001,\\\"lng\\\":8.78658}\",\"160\":\"{\\\"lat\\\":48.38633,\\\"lng\\\":8.726310000000002}\",\"161\":\"{\\\"lat\\\":48.39132000000001,\\\"lng\\\":8.728990000000001}\",\"162\":\"{\\\"lat\\\":48.39681,\\\"lng\\\":8.72934}\",\"163\":\"{\\\"lat\\\":48.40462,\\\"lng\\\":8.72687}\",\"164\":\"{\\\"lat\\\":48.409440000000004,\\\"lng\\\":8.72638}\",\"165\":\"{\\\"lat\\\":48.414170000000006,\\\"lng\\\":8.728100000000001}\",\"166\":\"{\\\"lat\\\":48.419720000000005,\\\"lng\\\":8.73384}\",\"167\":\"{\\\"lat\\\":48.42607,\\\"lng\\\":8.74263}\",\"168\":\"{\\\"lat\\\":48.439150000000005,\\\"lng\\\":8.754100000000001}\",\"169\":\"{\\\"lat\\\":48.451930000000004,\\\"lng\\\":8.770090000000001}\",\"60\":\"{\\\"lat\\\":47.869330000000005,\\\"lng\\\":8.779570000000001}\",\"61\":\"{\\\"lat\\\":47.87382,\\\"lng\\\":8.776670000000001}\",\"62\":\"{\\\"lat\\\":47.883300000000006,\\\"lng\\\":8.775910000000001}\",\"63\":\"{\\\"lat\\\":47.88678,\\\"lng\\\":8.774170000000002}\",\"64\":\"{\\\"lat\\\":47.89029000000001,\\\"lng\\\":8.769670000000001}\",\"65\":\"{\\\"lat\\\":47.89209,\\\"lng\\\":8.76415}\",\"66\":\"{\\\"lat\\\":47.89238,\\\"lng\\\":8.75845}\",\"67\":\"{\\\"lat\\\":47.89143000000001,\\\"lng\\\":8.75319}\",\"68\":\"{\\\"lat\\\":47.889230000000005,\\\"lng\\\":8.74788}\",\"69\":\"{\\\"lat\\\":47.88777,\\\"lng\\\":8.74102}\",\"170\":\"{\\\"lat\\\":48.458270000000006,\\\"lng\\\":8.777930000000001}\",\"171\":\"{\\\"lat\\\":48.46435,\\\"lng\\\":8.78355}\",\"172\":\"{\\\"lat\\\":48.47037,\\\"lng\\\":8.788}\",\"173\":\"{\\\"lat\\\":48.47655,\\\"lng\\\":8.79443}\",\"174\":\"{\\\"lat\\\":48.48087,\\\"lng\\\":8.80213}\",\"175\":\"{\\\"lat\\\":48.48382,\\\"lng\\\":8.811150000000001}\",\"176\":\"{\\\"lat\\\":48.48644,\\\"lng\\\":8.8201}\",\"177\":\"{\\\"lat\\\":48.48969,\\\"lng\\\":8.82689}\",\"178\":\"{\\\"lat\\\":48.49562,\\\"lng\\\":8.83453}\",\"179\":\"{\\\"lat\\\":48.5018,\\\"lng\\\":8.84115}\",\"70\":\"{\\\"lat\\\":47.888380000000005,\\\"lng\\\":8.734210000000001}\",\"71\":\"{\\\"lat\\\":47.89119,\\\"lng\\\":8.727}\",\"72\":\"{\\\"lat\\\":47.89224,\\\"lng\\\":8.7208}\",\"73\":\"{\\\"lat\\\":47.892500000000005,\\\"lng\\\":8.71353}\",\"74\":\"{\\\"lat\\\":47.89412,\\\"lng\\\":8.70829}\",\"75\":\"{\\\"lat\\\":47.89669000000001,\\\"lng\\\":8.704460000000001}\",\"76\":\"{\\\"lat\\\":47.900490000000005,\\\"lng\\\":8.701770000000002}\",\"77\":\"{\\\"lat\\\":47.90466000000001,\\\"lng\\\":8.698110000000002}\",\"78\":\"{\\\"lat\\\":47.90888,\\\"lng\\\":8.692060000000001}\",\"79\":\"{\\\"lat\\\":47.9127,\\\"lng\\\":8.68299}\",\"180\":\"{\\\"lat\\\":48.50898,\\\"lng\\\":8.85223}\",\"181\":\"{\\\"lat\\\":48.513090000000005,\\\"lng\\\":8.861730000000001}\",\"182\":\"{\\\"lat\\\":48.51709,\\\"lng\\\":8.873890000000001}\",\"183\":\"{\\\"lat\\\":48.521,\\\"lng\\\":8.88157}\",\"184\":\"{\\\"lat\\\":48.526070000000004,\\\"lng\\\":8.88725}\",\"185\":\"{\\\"lat\\\":48.531980000000004,\\\"lng\\\":8.890550000000001}\",\"186\":\"{\\\"lat\\\":48.537180000000006,\\\"lng\\\":8.89133}\",\"187\":\"{\\\"lat\\\":48.54117,\\\"lng\\\":8.89072}\",\"188\":\"{\\\"lat\\\":48.54759000000001,\\\"lng\\\":8.88911}\",\"189\":\"{\\\"lat\\\":48.55366,\\\"lng\\\":8.88954}\",\"80\":\"{\\\"lat\\\":47.919450000000005,\\\"lng\\\":8.6758}\",\"81\":\"{\\\"lat\\\":47.9234,\\\"lng\\\":8.66787}\",\"82\":\"{\\\"lat\\\":47.92512000000001,\\\"lng\\\":8.66154}\",\"83\":\"{\\\"lat\\\":47.92624000000001,\\\"lng\\\":8.65286}\",\"84\":\"{\\\"lat\\\":47.927150000000005,\\\"lng\\\":8.64489}\",\"85\":\"{\\\"lat\\\":47.929320000000004,\\\"lng\\\":8.63671}\",\"86\":\"{\\\"lat\\\":47.93301,\\\"lng\\\":8.62936}\",\"87\":\"{\\\"lat\\\":47.93675,\\\"lng\\\":8.624740000000001}\",\"88\":\"{\\\"lat\\\":47.94026,\\\"lng\\\":8.62186}\",\"89\":\"{\\\"lat\\\":47.9461,\\\"lng\\\":8.619330000000001}\",\"190\":\"{\\\"lat\\\":48.55847000000001,\\\"lng\\\":8.89156}\",\"191\":\"{\\\"lat\\\":48.56575,\\\"lng\\\":8.897}\",\"192\":\"{\\\"lat\\\":48.57114000000001,\\\"lng\\\":8.899460000000001}\",\"193\":\"{\\\"lat\\\":48.57733,\\\"lng\\\":8.899780000000002}\",\"194\":\"{\\\"lat\\\":48.5983,\\\"lng\\\":8.89633}\",\"195\":\"{\\\"lat\\\":48.60305,\\\"lng\\\":8.8963}\",\"196\":\"{\\\"lat\\\":48.606570000000005,\\\"lng\\\":8.89724}\",\"197\":\"{\\\"lat\\\":48.61103000000001,\\\"lng\\\":8.90056}\",\"198\":\"{\\\"lat\\\":48.61493,\\\"lng\\\":8.905240000000001}\",\"199\":\"{\\\"lat\\\":48.61984,\\\"lng\\\":8.910120000000001}\",\"90\":\"{\\\"lat\\\":47.95217,\\\"lng\\\":8.61912}\",\"91\":\"{\\\"lat\\\":47.96079,\\\"lng\\\":8.621080000000001}\",\"92\":\"{\\\"lat\\\":47.96936,\\\"lng\\\":8.620790000000001}\",\"93\":\"{\\\"lat\\\":47.97288,\\\"lng\\\":8.619760000000001}\",\"94\":\"{\\\"lat\\\":47.981,\\\"lng\\\":8.615200000000002}\",\"95\":\"{\\\"lat\\\":47.99042000000001,\\\"lng\\\":8.60861}\",\"96\":\"{\\\"lat\\\":47.999210000000005,\\\"lng\\\":8.60557}\",\"97\":\"{\\\"lat\\\":48.004290000000005,\\\"lng\\\":8.605160000000001}\",\"98\":\"{\\\"lat\\\":48.012010000000004,\\\"lng\\\":8.606390000000001}\",\"99\":\"{\\\"lat\\\":48.0193,\\\"lng\\\":8.609760000000001}\",\"200\":\"{\\\"lat\\\":48.62565000000001,\\\"lng\\\":8.913210000000001}\",\"201\":\"{\\\"lat\\\":48.63196000000001,\\\"lng\\\":8.91577}\",\"202\":\"{\\\"lat\\\":48.635070000000006,\\\"lng\\\":8.91876}\",\"203\":\"{\\\"lat\\\":48.64255000000001,\\\"lng\\\":8.93378}\",\"204\":\"{\\\"lat\\\":48.645450000000004,\\\"lng\\\":8.938450000000001}\",\"205\":\"{\\\"lat\\\":48.64851,\\\"lng\\\":8.946280000000002}\",\"206\":\"{\\\"lat\\\":48.657360000000004,\\\"lng\\\":8.95916}\",\"207\":\"{\\\"lat\\\":48.66185,\\\"lng\\\":8.96218}\",\"208\":\"{\\\"lat\\\":48.66702,\\\"lng\\\":8.963420000000001}\",\"209\":\"{\\\"lat\\\":48.675380000000004,\\\"lng\\\":8.96406}\",\"210\":\"{\\\"lat\\\":48.68025,\\\"lng\\\":8.96691}\",\"211\":\"{\\\"lat\\\":48.683870000000006,\\\"lng\\\":8.97321}\",\"212\":\"{\\\"lat\\\":48.686330000000005,\\\"lng\\\":8.98112}\",\"213\":\"{\\\"lat\\\":48.69169,\\\"lng\\\":8.9905}\",\"214\":\"{\\\"lat\\\":48.692980000000006,\\\"lng\\\":8.994240000000001}\",\"215\":\"{\\\"lat\\\":48.69393,\\\"lng\\\":9.008560000000001}\",\"216\":\"{\\\"lat\\\":48.695190000000004,\\\"lng\\\":9.01429}\",\"217\":\"{\\\"lat\\\":48.70067,\\\"lng\\\":9.02562}\",\"218\":\"{\\\"lat\\\":48.703070000000004,\\\"lng\\\":9.03509}\",\"219\":\"{\\\"lat\\\":48.70349,\\\"lng\\\":9.04073}\",\"220\":\"{\\\"lat\\\":48.70546,\\\"lng\\\":9.048760000000001}\",\"100\":\"{\\\"lat\\\":48.034530000000004,\\\"lng\\\":8.62039}\",\"221\":\"{\\\"lat\\\":48.708180000000006,\\\"lng\\\":9.05235}\",\"101\":\"{\\\"lat\\\":48.03971000000001,\\\"lng\\\":8.623090000000001}\",\"222\":\"{\\\"lat\\\":48.712300000000006,\\\"lng\\\":9.054120000000001}\",\"102\":\"{\\\"lat\\\":48.04560000000001,\\\"lng\\\":8.6243}\",\"223\":\"{\\\"lat\\\":48.716460000000005,\\\"lng\\\":9.05447}\",\"103\":\"{\\\"lat\\\":48.05156,\\\"lng\\\":8.62355}\",\"224\":\"{\\\"lat\\\":48.71885,\\\"lng\\\":9.055890000000002}\",\"104\":\"{\\\"lat\\\":48.05718,\\\"lng\\\":8.62092}\",\"225\":\"{\\\"lat\\\":48.721740000000004,\\\"lng\\\":9.059650000000001}\",\"105\":\"{\\\"lat\\\":48.061840000000004,\\\"lng\\\":8.617030000000002}\",\"226\":\"{\\\"lat\\\":48.7252,\\\"lng\\\":9.066080000000001}\",\"106\":\"{\\\"lat\\\":48.06804,\\\"lng\\\":8.60956}\",\"227\":\"{\\\"lat\\\":48.728030000000004,\\\"lng\\\":9.073020000000001}\",\"107\":\"{\\\"lat\\\":48.07694,\\\"lng\\\":8.59971}\",\"228\":\"{\\\"lat\\\":48.72908,\\\"lng\\\":9.07815}\",\"108\":\"{\\\"lat\\\":48.08258000000001,\\\"lng\\\":8.5959}\",\"229\":\"{\\\"lat\\\":48.72941,\\\"lng\\\":9.084280000000001}\",\"109\":\"{\\\"lat\\\":48.090300000000006,\\\"lng\\\":8.592820000000001}\"}";

        firestoreService.getParkingLotsOnRoute(pathAsJson);

    }

}
