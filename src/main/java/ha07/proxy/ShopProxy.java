package ha07.proxy;

import ha07.server.ShopServer;
import org.fulib.yaml.EventSource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.fulib.yaml.EventSource.EVENT_KEY;
import static org.fulib.yaml.EventSource.EVENT_TYPE;

public class ShopProxy {

	public static final String ADD_PRODUCT_TO_SHOP = "addProductToShop";
	public static final String PRODUCT = "product";
	public static final String NUMBER_OF_ITEMS = "numberOfItems";
	private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

	public void addProductToShop(String lotId, String productName, int size) {
		LinkedHashMap<String, String> event = new LinkedHashMap<>();
		event.put(EVENT_TYPE, ADD_PRODUCT_TO_SHOP);
		event.put(EVENT_KEY, lotId);
		event.put(PRODUCT, productName);
		event.put(NUMBER_OF_ITEMS, "" + size);
		String yaml = EventSource.encodeYaml(event);
		sendRequest(yaml);
	}

	private void sendRequest(String yaml) {
		String u = "http://localhost:" + ShopServer.port + "/do";
		try {
			System.out.println("ShopProxy sendRequest " + u);
			System.out.println(yaml);

			URL url = new URL(u);

			URLConnection con = url.openConnection();
			HttpURLConnection http = (HttpURLConnection) con;

			http.setRequestMethod("POST");
			http.setDoInput(true);

			byte[] out = yaml.getBytes(StandardCharsets.UTF_8);
			int length = out.length;

			http.setFixedLengthStreamingMode(length);
			http.setRequestProperty("Content-Type", "application/yaml; charset=UTF-8");
			http.connect();

			try (OutputStream os = http.getOutputStream()) {
				os.write(out);
			}

			InputStream inputStream = http.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
			String line = bufferedReader.readLine();
			bufferedReader.close();
			System.out.println(line);
		} catch (Exception e) {
			executor.schedule(() -> sendRequest(yaml), 60, TimeUnit.SECONDS);
		}
	}
}
