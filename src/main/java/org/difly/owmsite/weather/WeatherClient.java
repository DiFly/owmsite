package org.difly.owmsite.weather;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class WeatherClient {
//    private String API_KEY = "xxx";
    private String API_KEY = "b6907d289e10d714a6e88b30761fae22";
//    private String hostURL = "api.openweathermap.org/data/2.5";
    private String hostURL = "samples.openweathermap.org/data/2.5";
    private String pathURL = "/weather";
    private String cityNameParamURL = "London";
    private URI uri;

    //Create Client
    public void createClient() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(uri);
        CloseableHttpResponse response = httpclient.execute(httpget);;
        try {
            System.out.println(response.getProtocolVersion());
            System.out.println(response.getStatusLine().getStatusCode());
            System.out.println(response.getStatusLine().getReasonPhrase());
            System.out.println(response.getStatusLine().toString());
            String str = getEntityToString(response.getEntity().getContent());
            System.out.println(str);
        } finally {
            response.close();
        }
    }

    private String getEntityToString(InputStream inputStream){
        String body = null;
        InputStream in = inputStream;
//        String encoding = con.getContentEncoding();
        String encoding = "UTF-8";
//        encoding = encoding == null ? "UTF-8" : encoding;
        try {
            body = IOUtils.toString(in, encoding);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }

    //Create URI
    public void createURI(){
        try {
            // https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22 (samples API key)
            uri = new URIBuilder()
                    .setScheme("https")
                    .setHost(hostURL)
                    .setPath(pathURL)
                    .setParameter("q", cityNameParamURL)
                    .setParameter("appid", API_KEY)
                    .build();
            System.out.println(uri.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    //ToDo Delete before production
    public WeatherClient() {}

    public WeatherClient(String API_KEY, String cityNameParamURL) {
        this.API_KEY = API_KEY;
        this.cityNameParamURL = cityNameParamURL;
    }

}
