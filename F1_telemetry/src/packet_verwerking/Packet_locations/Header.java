package packet_verwerking.Packet_locations;

public class Header {
	public static byte[] m_packetFormat = new byte[] {0, 1};
	public static byte[] m_gameMajorVersion = new byte[] {2};
	public static byte[] m_gameMinorVersion = new byte[] {3};
	public static byte[] m_packetVersion = new byte[] {4};
	public static byte[] m_packetId = new byte[] {5};
	public static byte[] m_sessionUID = new byte[] {6, 7, 8, 9, 10, 11, 12, 13};
	public static byte[] m_sessionTime = new byte[] {14, 15, 16, 17};
	public static byte[] m_frameIdentifier = new byte[] {18, 19, 20, 21};
	public static byte[] m_playerCarIndex = new byte[] {22};
	public static byte[] m_secondaryPlayerCarIndex = new byte[] {23};
}
