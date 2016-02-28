package android.softfan.clientCmd;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import android.softfan.client.ClientCmdDo;
import android.softfan.client.ClientException;
import android.softfan.cmd.doOnLine;
import android.softfan.util.systemUtil;

public class sessionOnLine {

	private String	sessionId;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public boolean onLine() throws ClientException {
		try {
			doOnLine onLineCmd = new doOnLine();
			onLineCmd.setSessionId(sessionId);
			String xmlData = ClientCmdDo.DoExec(onLineCmd);

			StringReader cmd_in_buffer = new StringReader(xmlData);
			InputSource is = new InputSource(cmd_in_buffer);

			DocumentBuilderFactory docBFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBFactory.newDocumentBuilder();

			Document doc = docBuilder.parse(is);

			Element docroot = doc.getDocumentElement();

			if (!docroot.getTagName().equals("RespondRoot"))
				throw new ClientException("信息格式错误");

			String result = docroot.getTextContent();
			return "true".equals(result);
		} catch (ClientException e) {
			throw e;
		} catch (java.lang.Exception e) {
			throw new ClientException(systemUtil.getErrorMessage(e));
		} catch (java.lang.Throwable e) {
			throw new ClientException(systemUtil.getErrorMessage(e));
		}
	}

}
