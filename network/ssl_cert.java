import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.InvalidKeyException;
import java.security.SignatureException;

public class ssl_cert
{
	public static void check_date(X509Certificate cert) throws Exception
	{
		try {
			cert.checkValidity();
		} catch (CertificateExpiredException e) {
			System.out.println("Certificate "
				+ cert.getSerialNumber()
				+ " expired: "
				+ cert.getNotAfter());
		} catch (CertificateNotYetValidException e) {
			System.out.println("Certificate"
				+ cert.getSerialNumber()
				+ " can't be used before "
				+ cert.getNotBefore());
		}
	}

	public static void check_self_signed(X509Certificate cert) throws Exception
	{
		try {
			PublicKey key = cert.getPublicKey();
			cert.verify(key);
			System.out.println("Certificate "
				+ cert.getSerialNumber()
				+ " is self-signed");
		} catch (SignatureException sigEx) {
			//
		} catch (InvalidKeyException keyEx) {
			//
		}
	}

	public static void main(String []args) throws Exception
	{
		SSLContext ctx = SSLContext.getInstance("TLS");
		ctx.init(new KeyManager[0],
			 new TrustManager[] { new DefaultTrustManager() },
			 new SecureRandom());
		SSLContext.setDefault(ctx);
		URL url = new URL(args[0]);
		HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();

		conn.setHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String arg0,
					      SSLSession arg1)
			{
				return true;
			}
		});

		conn.getResponseCode();

		Certificate[] certs = conn.getServerCertificates();

		for (Certificate cert : certs) {
			System.out.println("***************");
			X509Certificate x509_cert = (X509Certificate)cert;

			check_date(x509_cert);
			check_self_signed(x509_cert);
		}
	}

	private static class DefaultTrustManager implements X509TrustManager
	{
		@Override
		public void checkClientTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {}

		@Override
		public void checkServerTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {}

		@Override
		public X509Certificate[] getAcceptedIssuers()
		{
			return null;
		}
	}
}
