package no.hvl.dat110.rpc;

import static org.junit.Assert.assertArrayEquals;

import java.nio.ByteBuffer;
import java.util.Arrays;

import no.hvl.dat110.TODO;

public class RPCUtils {

	// Utility methods for marshalling and marshalling of parameters and return
	// values
	// in RPC request and RPC responses
	// data bytearrays and return byte arrays is according to the
	// RPC message syntax [rpcid,parameter/return value]

	public static byte[] marshallString(byte rpcid, String str) {

		byte[] encoded = new byte[str.getBytes().length + 1];

		encoded[0] = rpcid;

		for (int i = 0; i < str.getBytes().length; i++) {
			encoded[i + 1] = str.getBytes()[i];
		}

		return encoded;
	}

	public static String unmarshallString(byte[] data) {

		data = Arrays.copyOfRange(data, 1, data.length);

		String decoded = new String(data);

		return decoded;
	}

	public static byte[] marshallVoid(byte rpcid) {

		byte[] encoded = new byte[1];
		encoded[0] = rpcid;

		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {

		byte[] temp = new byte[data.length - 1];

		for (int i = 1; i < data.length; i++) {
			temp[i - 1] = data[i];
		}

		data = temp;

	}

	public static byte[] marshallBoolean(byte rpcid, boolean b) {

		byte[] encoded = new byte[2];

		encoded[0] = rpcid;

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(byte rpcid, int x) {

		byte[] data = ByteBuffer.allocate(4).putInt(x).array();
		byte[] encoded = new byte[data.length + 1];

		encoded[0] = rpcid;

		for (int i = 0; i < data.length; i++) {
			encoded[i + 1] = data[i];
		}

		return encoded;
	}

	public static int unmarshallInteger(byte[] data) {

		int decoded = ByteBuffer.wrap(data, 1, data.length - 1).getInt();

		return decoded;

	}
}
