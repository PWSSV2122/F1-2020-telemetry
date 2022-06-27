package packet_struct;

public class Header {

		private short packetFormat;
		private byte gameMajorVersion;
		private byte gameMinorVersion;
		private byte packetVersion;
		private byte packetId;
		private long sessionUID;
		private float sessionTime;
		private int frameIdentifier;
		private byte playerCarIndex;
		private byte secondaryPlayerCarIndex;
		
		public Header(short packetFormat, byte gameMajorVersion, byte gameMinorVersion, byte packetVersion, byte packetId, long sessionUID, float sessionTime, 
				int frameIdentifier, byte playerCarIndex, byte secondaryPlayerCarIndex) {
			this.packetFormat = packetFormat;
			this.gameMajorVersion = gameMajorVersion;
			this.gameMinorVersion = gameMinorVersion;
			this.packetVersion = packetVersion;
			this.packetId = packetId;
			this.sessionUID = sessionUID;
			this.sessionTime = sessionTime;
			this.frameIdentifier = frameIdentifier;
			this.playerCarIndex = playerCarIndex;
			this.secondaryPlayerCarIndex = secondaryPlayerCarIndex;
		}
		
		public short getPacketFormat() {
			return packetFormat;
		}
		
		public byte getGameMajorVersion() {
			return gameMajorVersion;
		}
		
		public byte getGameMinorVersion() {
			return gameMinorVersion;
		}
		
		public byte getPacketVersion() {
			return packetVersion;
		}
		
		public byte getPacketId() {
			return packetId;
		}
		
		public long getSessionUID() {
			return sessionUID;
		}
		
		public float getSessionTime() {
			return sessionTime;
		}
		
		public int getFrameIdentifier() {
			return frameIdentifier;
		}
		
		public byte getPlayerCarIndex() {
			return playerCarIndex;
		}
		
		public byte getSecondaryPlayerCarIndex() {
			return secondaryPlayerCarIndex;
		}
}