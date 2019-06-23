package ha07.base;

import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class HA07Server {


	public static String getBody(HttpExchange exchange) {
		try {
			URI uri = exchange.getRequestURI();
			InputStream body = exchange.getRequestBody();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(body, StandardCharsets.UTF_8));
			StringBuilder stringBuilder = new StringBuilder();
			while (true) {
				String line = bufferedReader.readLine();
				if (line == null) {
					break;
				}
				stringBuilder.append(line).append("\n");
			}
			return stringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected static void writeAnswer(HttpExchange exchange, String resp) {
		try {
			byte[] bytes = resp.getBytes(StandardCharsets.UTF_8);
			exchange.sendResponseHeaders(200, bytes.length);
			OutputStream outputStream = exchange.getResponseBody();
			outputStream.write(bytes);
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static String sendRequest(String url, String yaml) {
		try {
			System.out.println("sendRequest " + url);

			URL u = new URL(url);

			URLConnection con = u.openConnection();
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
			return line;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected static void print(String msg) {
		System.out.println(msg);
	}

}
