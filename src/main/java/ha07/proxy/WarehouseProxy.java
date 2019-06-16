package ha07.proxy;

import ha07.server.WarehouseServer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WarehouseProxy {

	private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();


	private void sendRequest(String yaml) {

		String u = "http://localhost:" + WarehouseServer.port + "/do";
		try {
			System.out.println("ShopProxy sendRequest " + u);
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
		} catch (Exception e) {
			executor.schedule(() -> sendRequest(yaml), 60, TimeUnit.SECONDS);
		}
	}
}
