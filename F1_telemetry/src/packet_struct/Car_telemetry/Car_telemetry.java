package packet_struct.Car_telemetry;

public class Car_telemetry {

	private int buttonStatus;
	private byte mfdPanelIndex;
	private byte mfdPanelIndexSecondaryPlayer;
	private byte suggestedGear;
	
	public Car_telemetry(int buttonStatus, byte mfdPanelIndex, byte mfdPanelIndexSecondaryPlayer, byte suggestedGear) {
		this.buttonStatus = buttonStatus;
		this.mfdPanelIndex = mfdPanelIndex;
		this.mfdPanelIndexSecondaryPlayer = mfdPanelIndexSecondaryPlayer;
		this.suggestedGear = suggestedGear;
	}
	
	public int getButtonStatus() {
		return buttonStatus;
	}
	
	public byte getMfdPanelIndex() {
		return mfdPanelIndex;
	}
	
	public byte getMfdPanelIndexSecondaryPlayer() {
		return mfdPanelIndexSecondaryPlayer;
	}
	
	public byte getSuggestedGear() {
		return suggestedGear;
	}
}
