package com.syro.blepulltoscan.util;

import java.util.HashMap;

/**
 * Created by Syro on 2016-01-06.
 */
public class GattProfile {
	// All Services:
	public static final String ALERT_NOTIFICATION_SERVICE = "00001811-0000-1000-8000-00805f9b34fb";
	public static final String AUTOMATION_IO = "00001815-0000-1000-8000-00805f9b34fb";
	public static final String BATTERY_SERVICE = "0000180f-0000-1000-8000-00805f9b34fb";
	public static final String BLOOD_PRESSURE = "00001810-0000-1000-8000-00805f9b34fb";
	public static final String BODY_COMPOSITION = "0000181b-0000-1000-8000-00805f9b34fb";
	public static final String BOND_MANAGEMENT = "0000181e-0000-1000-8000-00805f9b34fb";
	public static final String CONTINUOUS_GLUCOSE_MONITORING = "0000181f-0000-1000-8000-00805f9b34fb";
	public static final String CURRENT_TIME_SERVICE = "00001805-0000-1000-8000-00805f9b34fb";
	public static final String CYCLING_POWER = "00001818-0000-1000-8000-00805f9b34fb";
	public static final String CYCLING_SPEED_AND_CADENCE = "00001816-0000-1000-8000-00805f9b34fb";
	public static final String DEVICE_INFORMATION = "0000180a-0000-1000-8000-00805f9b34fb";
	public static final String ENVIRONMENTAL_SENSING = "0000181a-0000-1000-8000-00805f9b34fb";
	public static final String GENERIC_ACCESS = "00001800-0000-1000-8000-00805f9b34fb";
	public static final String GENERIC_ATTRIBUTE = "00001801-0000-1000-8000-00805f9b34fb";
	public static final String GLUCOSE = "00001808-0000-1000-8000-00805f9b34fb";
	public static final String HEALTH_THERMOMETER = "00001809-0000-1000-8000-00805f9b34fb";
	public static final String HEART_RATE = "0000180d-0000-1000-8000-00805f9b34fb";
	public static final String HTTP_PROXY = "00001823-0000-1000-8000-00805f9b34fb";
	public static final String HUMAN_INTERFACE_DEVICE = "00001812-0000-1000-8000-00805f9b34fb";
	public static final String IMMEDIATE_ALERT = "00001802-0000-1000-8000-00805f9b34fb";
	public static final String INDOOR_POSITIONING = "00001821-0000-1000-8000-00805f9b34fb";
	public static final String INTERNET_PROTOCOL_SUPPORT = "00001820-0000-1000-8000-00805f9b34fb";
	public static final String LINK_LOSS = "00001803-0000-1000-8000-00805f9b34fb";
	public static final String LOCATION_AND_NAVIGATION = "00001819-0000-1000-8000-00805f9b34fb";
	public static final String NEXT_DST_CHANGE_SERVICE = "00001807-0000-1000-8000-00805f9b34fb";
	public static final String OBJECT_TRANSFER = "00001825-0000-1000-8000-00805f9b34fb";
	public static final String PHONE_ALERT_STATUS_SERVICE = "0000180e-0000-1000-8000-00805f9b34fb";
	public static final String PULSE_OXIMETER = "00001822-0000-1000-8000-00805f9b34fb";
	public static final String REFERENCE_TIME_UPDATE_SERVICE = "00001806-0000-1000-8000-00805f9b34fb";
	public static final String RUNNING_SPEED_AND_CADENCE = "00001814-0000-1000-8000-00805f9b34fb";
	public static final String SCAN_PARAMETERS = "00001813-0000-1000-8000-00805f9b34fb";
	public static final String TRANSPORT_DISCOVERY = "00001824-0000-1000-8000-00805f9b34fb";
	public static final String TX_POWER = "00001804-0000-1000-8000-00805f9b34fb";
	public static final String USER_DATA = "0000181c-0000-1000-8000-00805f9b34fb";
	public static final String WEIGHT_SCALE = "0000181d-0000-1000-8000-00805f9b34fb";

	//--- custom service ---:
	public static final String NORDIC_UART = "6e400001-b5a3-f393-e0a9-e50e24dcca9e";

	// All Characteristics:
	public static final String AEROBIC_HEART_RATE_LOWER_LIMIT = "00002a7e-0000-1000-8000-00805f9b34fb";
	public static final String AEROBIC_HEART_RATE_UPPER_LIMIT = "00002a84-0000-1000-8000-00805f9b34fb";
	public static final String AEROBIC_THRESHOLD = "00002a7f-0000-1000-8000-00805f9b34fb";
	public static final String AGE = "00002a80-0000-1000-8000-00805f9b34fb";
	public static final String AGGREGATE = "00002a5a-0000-1000-8000-00805f9b34fb";
	public static final String ALERT_CATEGORY_ID = "00002a43-0000-1000-8000-00805f9b34fb";
	public static final String ALERT_CATEGORY_ID_BIT_MASK = "00002a42-0000-1000-8000-00805f9b34fb";
	public static final String ALERT_LEVEL = "00002a06-0000-1000-8000-00805f9b34fb";
	public static final String ALERT_NOTIFICATION_CONTROL_POINT = "00002a44-0000-1000-8000-00805f9b34fb";
	public static final String ALERT_STATUS = "00002a3f-0000-1000-8000-00805f9b34fb";
	public static final String ALTITUDE = "00002ab3-0000-1000-8000-00805f9b34fb";
	public static final String ANAEROBIC_HEART_RATE_LOWER_LIMIT = "00002a81-0000-1000-8000-00805f9b34fb";
	public static final String ANAEROBIC_HEART_RATE_UPPER_LIMIT = "00002a82-0000-1000-8000-00805f9b34fb";
	public static final String ANAEROBIC_THRESHOLD = "00002a83-0000-1000-8000-00805f9b34fb";
	public static final String ANALOG = "00002a58-0000-1000-8000-00805f9b34fb";
	public static final String APPARENT_WIND_DIRECTION = "00002a73-0000-1000-8000-00805f9b34fb";
	public static final String APPARENT_WIND_SPEED = "00002a72-0000-1000-8000-00805f9b34fb";
	public static final String APPEARANCE = "00002a01-0000-1000-8000-00805f9b34fb";
	public static final String BAROMETRIC_PRESSURE_TREND = "00002aa3-0000-1000-8000-00805f9b34fb";
	public static final String BATTERY_LEVEL = "00002a19-0000-1000-8000-00805f9b34fb";
	public static final String BLOOD_PRESSURE_FEATURE = "00002a49-0000-1000-8000-00805f9b34fb";
	public static final String BLOOD_PRESSURE_MEASUREMENT = "00002a35-0000-1000-8000-00805f9b34fb";
	public static final String BODY_COMPOSITION_FEATURE = "00002a9b-0000-1000-8000-00805f9b34fb";
	public static final String BODY_COMPOSITION_MEASUREMENT = "00002a9c-0000-1000-8000-00805f9b34fb";
	public static final String BODY_SENSOR_LOCATION = "00002a38-0000-1000-8000-00805f9b34fb";
	public static final String BOND_MANAGEMENT_CONTROL_POINT = "00002aa4-0000-1000-8000-00805f9b34fb";
	public static final String BOND_MANAGEMENT_FEATURE = "00002aa5-0000-1000-8000-00805f9b34fb";
	public static final String BOOT_KEYBOARD_INPUT_REPORT = "00002a22-0000-1000-8000-00805f9b34fb";
	public static final String BOOT_KEYBOARD_OUTPUT_REPORT = "00002a32-0000-1000-8000-00805f9b34fb";
	public static final String BOOT_MOUSE_INPUT_REPORT = "00002a33-0000-1000-8000-00805f9b34fb";
	public static final String CENTRAL_ADDRESS_RESOLUTION = "00002aa6-0000-1000-8000-00805f9b34fb";
	public static final String CGM_FEATURE = "00002aa8-0000-1000-8000-00805f9b34fb";
	public static final String CGM_MEASUREMENT = "00002aa7-0000-1000-8000-00805f9b34fb";
	public static final String CGM_SESSION_RUN_TIME = "00002aab-0000-1000-8000-00805f9b34fb";
	public static final String CGM_SESSION_START_TIME = "00002aaa-0000-1000-8000-00805f9b34fb";
	public static final String CGM_SPECIFIC_OPS_CONTROL_POINT = "00002aac-0000-1000-8000-00805f9b34fb";
	public static final String CGM_STATUS = "00002aa9-0000-1000-8000-00805f9b34fb";
	public static final String CSC_FEATURE = "00002a5c-0000-1000-8000-00805f9b34fb";
	public static final String CSC_MEASUREMENT = "00002a5b-0000-1000-8000-00805f9b34fb";
	public static final String CURRENT_TIME = "00002a2b-0000-1000-8000-00805f9b34fb";
	public static final String CYCLING_POWER_CONTROL_POINT = "00002a66-0000-1000-8000-00805f9b34fb";
	public static final String CYCLING_POWER_FEATURE = "00002a65-0000-1000-8000-00805f9b34fb";
	public static final String CYCLING_POWER_MEASUREMENT = "00002a63-0000-1000-8000-00805f9b34fb";
	public static final String CYCLING_POWER_VECTOR = "00002a64-0000-1000-8000-00805f9b34fb";
	public static final String DATABASE_CHANGE_INCREMENT = "00002a99-0000-1000-8000-00805f9b34fb";
	public static final String DATE_OF_BIRTH = "00002a85-0000-1000-8000-00805f9b34fb";
	public static final String DATE_OF_THRESHOLD_ASSESSMENT = "00002a86-0000-1000-8000-00805f9b34fb";
	public static final String DATE_TIME = "00002a08-0000-1000-8000-00805f9b34fb";
	public static final String DAY_DATE_TIME = "00002a0a-0000-1000-8000-00805f9b34fb";
	public static final String DAY_OF_WEEK = "00002a09-0000-1000-8000-00805f9b34fb";
	public static final String DESCRIPTOR_VALUE_CHANGED = "00002a7d-0000-1000-8000-00805f9b34fb";
	public static final String DEVICE_NAME = "00002a00-0000-1000-8000-00805f9b34fb";
	public static final String DEW_POINT = "00002a7b-0000-1000-8000-00805f9b34fb";
	public static final String DIGITAL = "00002a56-0000-1000-8000-00805f9b34fb";
	public static final String DST_OFFSET = "00002a0d-0000-1000-8000-00805f9b34fb";
	public static final String ELEVATION = "00002a6c-0000-1000-8000-00805f9b34fb";
	public static final String EMAIL_ADDRESS = "00002a87-0000-1000-8000-00805f9b34fb";
	public static final String EXACT_TIME_256 = "00002a0c-0000-1000-8000-00805f9b34fb";
	public static final String FAT_BURN_HEART_RATE_LOWER_LIMIT = "00002a88-0000-1000-8000-00805f9b34fb";
	public static final String FAT_BURN_HEART_RATE_UPPER_LIMIT = "00002a89-0000-1000-8000-00805f9b34fb";
	public static final String FIRMWARE_REVISION_STRING = "00002a26-0000-1000-8000-00805f9b34fb";
	public static final String FIRST_NAME = "00002a8a-0000-1000-8000-00805f9b34fb";
	public static final String FIVE_ZONE_HEART_RATE_LIMITS = "00002a8b-0000-1000-8000-00805f9b34fb";
	public static final String FLOOR_NUMBER = "00002ab2-0000-1000-8000-00805f9b34fb";
	public static final String GENDER = "00002a8c-0000-1000-8000-00805f9b34fb";
	public static final String GLUCOSE_FEATURE = "00002a51-0000-1000-8000-00805f9b34fb";
	public static final String GLUCOSE_MEASUREMENT = "00002a18-0000-1000-8000-00805f9b34fb";
	public static final String GLUCOSE_MEASUREMENT_CONTEXT = "00002a34-0000-1000-8000-00805f9b34fb";
	public static final String GUST_FACTOR = "00002a74-0000-1000-8000-00805f9b34fb";
	public static final String HARDWARE_REVISION_STRING = "00002a27-0000-1000-8000-00805f9b34fb";
	public static final String HEART_RATE_CONTROL_POINT = "00002a39-0000-1000-8000-00805f9b34fb";
	public static final String HEART_RATE_MAX = "00002a8d-0000-1000-8000-00805f9b34fb";
	public static final String HEART_RATE_MEASUREMENT = "00002a37-0000-1000-8000-00805f9b34fb";
	public static final String HEAT_INDEX = "00002a7a-0000-1000-8000-00805f9b34fb";
	public static final String HEIGHT = "00002a8e-0000-1000-8000-00805f9b34fb";
	public static final String HID_CONTROL_POINT = "00002a4c-0000-1000-8000-00805f9b34fb";
	public static final String HID_INFORMATION = "00002a4a-0000-1000-8000-00805f9b34fb";
	public static final String HIP_CIRCUMFERENCE = "00002a8f-0000-1000-8000-00805f9b34fb";
	public static final String HTTP_CONTROL_POINT = "00002aba-0000-1000-8000-00805f9b34fb";
	public static final String HTTP_ENTITY_BODY = "00002ab9-0000-1000-8000-00805f9b34fb";
	public static final String HTTP_HEADERS = "00002ab7-0000-1000-8000-00805f9b34fb";
	public static final String HTTP_STATUS_CODE = "00002ab8-0000-1000-8000-00805f9b34fb";
	public static final String HTTPS_SECURITY = "00002abb-0000-1000-8000-00805f9b34fb";
	public static final String HUMIDITY = "00002a6f-0000-1000-8000-00805f9b34fb";
	public static final String IEEE_11073_20601_REGULATORY_CERTIFICATION_DATA_LIST = "00002a2a-0000-1000-8000-00805f9b34fb";
	public static final String INDOOR_POSITIONING_CONFIGURATION = "00002aad-0000-1000-8000-00805f9b34fb";
	public static final String INTERMEDIATE_CUFF_PRESSURE = "00002a36-0000-1000-8000-00805f9b34fb";
	public static final String INTERMEDIATE_TEMPERATURE = "00002a1e-0000-1000-8000-00805f9b34fb";
	public static final String IRRADIANCE = "00002a77-0000-1000-8000-00805f9b34fb";
	public static final String LANGUAGE = "00002aa2-0000-1000-8000-00805f9b34fb";
	public static final String LAST_NAME = "00002a90-0000-1000-8000-00805f9b34fb";
	public static final String LATITUDE = "00002aae-0000-1000-8000-00805f9b34fb";
	public static final String LN_CONTROL_POINT = "00002a6b-0000-1000-8000-00805f9b34fb";
	public static final String LN_FEATURE = "00002a6a-0000-1000-8000-00805f9b34fb";
	public static final String LOCAL_EAST_COORDINATE = "00002ab1-0000-1000-8000-00805f9b34fb";
	public static final String LOCAL_NORTH_COORDINATE = "00002ab0-0000-1000-8000-00805f9b34fb";
	public static final String LOCAL_TIME_INFORMATION = "00002a0f-0000-1000-8000-00805f9b34fb";
	public static final String LOCATION_AND_SPEED = "00002a67-0000-1000-8000-00805f9b34fb";
	public static final String LOCATION_NAME = "00002ab5-0000-1000-8000-00805f9b34fb";
	public static final String LONGITUDE = "00002aaf-0000-1000-8000-00805f9b34fb";
	public static final String MAGNETIC_DECLINATION = "00002a2c-0000-1000-8000-00805f9b34fb";
	public static final String MAGNETIC_FLUX_DENSITY_2D = "00002aa0-0000-1000-8000-00805f9b34fb";
	public static final String MAGNETIC_FLUX_DENSITY_3D = "00002aa1-0000-1000-8000-00805f9b34fb";
	public static final String MANUFACTURER_NAME_STRING = "00002a29-0000-1000-8000-00805f9b34fb";
	public static final String MAXIMUM_RECOMMENDED_HEART_RATE = "00002a91-0000-1000-8000-00805f9b34fb";
	public static final String MEASUREMENT_INTERVAL = "00002a21-0000-1000-8000-00805f9b34fb";
	public static final String MODEL_NUMBER_STRING = "00002a24-0000-1000-8000-00805f9b34fb";
	public static final String NAVIGATION = "00002a68-0000-1000-8000-00805f9b34fb";
	public static final String NEW_ALERT = "00002a46-0000-1000-8000-00805f9b34fb";
	public static final String OBJECT_ACTION_CONTROL_POINT = "00002ac5-0000-1000-8000-00805f9b34fb";
	public static final String OBJECT_CHANGED = "00002ac8-0000-1000-8000-00805f9b34fb";
	public static final String OBJECT_FIRST_CREATED = "00002ac1-0000-1000-8000-00805f9b34fb";
	public static final String OBJECT_ID = "00002ac3-0000-1000-8000-00805f9b34fb";
	public static final String OBJECT_LAST_MODIFIED = "00002ac2-0000-1000-8000-00805f9b34fb";
	public static final String OBJECT_LIST_CONTROL_POINT = "00002ac6-0000-1000-8000-00805f9b34fb";
	public static final String OBJECT_LIST_FILTER = "00002ac7-0000-1000-8000-00805f9b34fb";
	public static final String OBJECT_NAME = "00002abe-0000-1000-8000-00805f9b34fb";
	public static final String OBJECT_PROPERTIES = "00002ac4-0000-1000-8000-00805f9b34fb";
	public static final String OBJECT_SIZE = "00002ac0-0000-1000-8000-00805f9b34fb";
	public static final String OBJECT_TYPE = "00002abf-0000-1000-8000-00805f9b34fb";
	public static final String OTS_FEATURE = "00002abd-0000-1000-8000-00805f9b34fb";
	public static final String PERIPHERAL_PREFERRED_CONNECTION_PARAMETERS = "00002a04-0000-1000-8000-00805f9b34fb";
	public static final String PERIPHERAL_PRIVACY_FLAG = "00002a02-0000-1000-8000-00805f9b34fb";
	public static final String PLX_CONTINUOUS_MEASUREMENT = "00002a5f-0000-1000-8000-00805f9b34fb";
	public static final String PLX_FEATURES = "00002a60-0000-1000-8000-00805f9b34fb";
	public static final String PLX_SPOT_CHECK_MEASUREMENT = "00002a5e-0000-1000-8000-00805f9b34fb";
	public static final String PNP_ID = "00002a50-0000-1000-8000-00805f9b34fb";
	public static final String POLLEN_CONCENTRATION = "00002a75-0000-1000-8000-00805f9b34fb";
	public static final String POSITION_QUALITY = "00002a69-0000-1000-8000-00805f9b34fb";
	public static final String PRESSURE = "00002a6d-0000-1000-8000-00805f9b34fb";
	public static final String PROTOCOL_MODE = "00002a4e-0000-1000-8000-00805f9b34fb";
	public static final String RAINFALL = "00002a78-0000-1000-8000-00805f9b34fb";
	public static final String RECONNECTION_ADDRESS = "00002a03-0000-1000-8000-00805f9b34fb";
	public static final String RECORD_ACCESS_CONTROL_POINT = "00002a52-0000-1000-8000-00805f9b34fb";
	public static final String REFERENCE_TIME_INFORMATION = "00002a14-0000-1000-8000-00805f9b34fb";
	public static final String REPORT = "00002a4d-0000-1000-8000-00805f9b34fb";
	public static final String REPORT_MAP = "00002a4b-0000-1000-8000-00805f9b34fb";
	public static final String RESTING_HEART_RATE = "00002a92-0000-1000-8000-00805f9b34fb";
	public static final String RINGER_CONTROL_POINT = "00002a40-0000-1000-8000-00805f9b34fb";
	public static final String RINGER_SETTING = "00002a41-0000-1000-8000-00805f9b34fb";
	public static final String RSC_FEATURE = "00002a54-0000-1000-8000-00805f9b34fb";
	public static final String RSC_MEASUREMENT = "00002a53-0000-1000-8000-00805f9b34fb";
	public static final String SC_CONTROL_POINT = "00002a55-0000-1000-8000-00805f9b34fb";
	public static final String SCAN_INTERVAL_WINDOW = "00002a4f-0000-1000-8000-00805f9b34fb";
	public static final String SCAN_REFRESH = "00002a31-0000-1000-8000-00805f9b34fb";
	public static final String SENSOR_LOCATION = "00002a5d-0000-1000-8000-00805f9b34fb";
	public static final String SERIAL_NUMBER_STRING = "00002a25-0000-1000-8000-00805f9b34fb";
	public static final String SERVICE_CHANGED = "00002a05-0000-1000-8000-00805f9b34fb";
	public static final String SOFTWARE_REVISION_STRING = "00002a28-0000-1000-8000-00805f9b34fb";
	public static final String SPORT_TYPE_FOR_AEROBIC_AND_ANAEROBIC_THRESHOLDS = "00002a93-0000-1000-8000-00805f9b34fb";
	public static final String SUPPORTED_NEW_ALERT_CATEGORY = "00002a47-0000-1000-8000-00805f9b34fb";
	public static final String SUPPORTED_UNREAD_ALERT_CATEGORY = "00002a48-0000-1000-8000-00805f9b34fb";
	public static final String SYSTEM_ID = "00002a23-0000-1000-8000-00805f9b34fb";
	public static final String TDS_CONTROL_POINT = "00002abc-0000-1000-8000-00805f9b34fb";
	public static final String TEMPERATURE = "00002a6e-0000-1000-8000-00805f9b34fb";
	public static final String TEMPERATURE_MEASUREMENT = "00002a1c-0000-1000-8000-00805f9b34fb";
	public static final String TEMPERATURE_TYPE = "00002a1d-0000-1000-8000-00805f9b34fb";
	public static final String THREE_ZONE_HEART_RATE_LIMITS = "00002a94-0000-1000-8000-00805f9b34fb";
	public static final String TIME_ACCURACY = "00002a12-0000-1000-8000-00805f9b34fb";
	public static final String TIME_SOURCE = "00002a13-0000-1000-8000-00805f9b34fb";
	public static final String TIME_UPDATE_CONTROL_POINT = "00002a16-0000-1000-8000-00805f9b34fb";
	public static final String TIME_UPDATE_STATE = "00002a17-0000-1000-8000-00805f9b34fb";
	public static final String TIME_WITH_DST = "00002a11-0000-1000-8000-00805f9b34fb";
	public static final String TIME_ZONE = "00002a0e-0000-1000-8000-00805f9b34fb";
	public static final String TRUE_WIND_DIRECTION = "00002a71-0000-1000-8000-00805f9b34fb";
	public static final String TRUE_WIND_SPEED = "00002a70-0000-1000-8000-00805f9b34fb";
	public static final String TWO_ZONE_HEART_RATE_LIMIT = "00002a95-0000-1000-8000-00805f9b34fb";
	public static final String TX_POWER_LEVEL = "00002a07-0000-1000-8000-00805f9b34fb";
	public static final String UNCERTAINTY = "00002ab4-0000-1000-8000-00805f9b34fb";
	public static final String UNREAD_ALERT_STATUS = "00002a45-0000-1000-8000-00805f9b34fb";
	public static final String URI = "00002ab6-0000-1000-8000-00805f9b34fb";
	public static final String USER_CONTROL_POINT = "00002a9f-0000-1000-8000-00805f9b34fb";
	public static final String USER_INDEX = "00002a9a-0000-1000-8000-00805f9b34fb";
	public static final String UV_INDEX = "00002a76-0000-1000-8000-00805f9b34fb";
	public static final String VO2_MAX = "00002a96-0000-1000-8000-00805f9b34fb";
	public static final String WAIST_CIRCUMFERENCE = "00002a97-0000-1000-8000-00805f9b34fb";
	public static final String WEIGHT = "00002a98-0000-1000-8000-00805f9b34fb";
	public static final String WEIGHT_MEASUREMENT = "00002a9d-0000-1000-8000-00805f9b34fb";
	public static final String WEIGHT_SCALE_FEATURE = "00002a9e-0000-1000-8000-00805f9b34fb";
	public static final String WIND_CHILL = "00002a79-0000-1000-8000-00805f9b34fb";

	//--- custom characteristics ---:
	public static final String NORDIC_UART_TX = "6e400002-b5a3-f393-e0a9-e50e24dcca9e";
	public static final String NORDIC_UART_RX = "6e400003-b5a3-f393-e0a9-e50e24dcca9e";

	// All Descriptors:
	public static final String CHARACTERISTIC_EXTENDED_PROPERTIES = "00002900-0000-1000-8000-00805f9b34fb";
	public static final String CHARACTERISTIC_USER_DESCRIPTION = "00002901-0000-1000-8000-00805f9b34fb";
	public static final String CLIENT_CHARACTERISTIC_CONFIGURATION = "00002902-0000-1000-8000-00805f9b34fb";
	public static final String SERVER_CHARACTERISTIC_CONFIGURATION = "00002903-0000-1000-8000-00805f9b34fb";
	public static final String CHARACTERISTIC_PRESENTATION_FORMAT = "00002904-0000-1000-8000-00805f9b34fb";
	public static final String CHARACTERISTIC_AGGREGATE_FORMAT = "00002905-0000-1000-8000-00805f9b34fb";
	public static final String VALID_RANGE = "00002906-0000-1000-8000-00805f9b34fb";
	public static final String EXTERNAL_REPORT_REFERENCE = "00002907-0000-1000-8000-00805f9b34fb";
	public static final String REPORT_REFERENCE = "00002908-0000-1000-8000-00805f9b34fb";
	public static final String NUMBER_OF_DIGITALS = "00002909-0000-1000-8000-00805f9b34fb";
	public static final String VALUE_TRIGGER_SETTING = "0000290A-0000-1000-8000-00805f9b34fb";
	public static final String ENVIRONMENTAL_SENSING_CONFIGURATION = "0000290B-0000-1000-8000-00805f9b34fb";
	public static final String ENVIRONMENTAL_SENSING_MEASUREMENT = "0000290C-0000-1000-8000-00805f9b34fb";
	public static final String ENVIRONMENTAL_SENSING_TRIGGER_SETTING = "0000290D-0000-1000-8000-00805f9b34fb";
	public static final String TIME_TRIGGER_SETTING = "0000290E-0000-1000-8000-00805f9b34fb";



    /*
    Generic Attribute Profile(GATT):
    通过BLE连接，读写属性类小数据的Profile通用规范。现在所有的BLE应用Profile都是基于GATT的。

    Attribute Protocol(ATT):
    GATT是基于ATT Protocol的。ATT针对BLE设备做了专门的优化，具体就是在传输过程中使用尽量少的数据。每个属性都有一个唯一的UUID，属性将以characteristics and services的形式传输。

    Service:
    Characteristic的集合。
    例如一个service叫做"Heart Rate"，它可能包含多个Characteristics，例如有一个"Heart Rate Measurement"

    Characteristic:
    Characteristic可以理解为一个数据结构，它包括一个value、零至n个对value的描述（Descriptor）。

    Descriptor:
    对Characteristic's value的描述，例如范围、计量单位等。
    */


	private static HashMap<String, String> attributes = new HashMap();

	static {
		// Some Services:
		attributes.put(BATTERY_SERVICE, "Battery Service");
		attributes.put(CURRENT_TIME_SERVICE, "Current Time Service");
		attributes.put(DEVICE_INFORMATION, "Device Information Service");
		attributes.put(HEART_RATE, "Heart Rate Service");
		attributes.put(HEALTH_THERMOMETER, "Health Thermometer");
		attributes.put(GENERIC_ACCESS, "Generic Access");
		attributes.put(GENERIC_ATTRIBUTE, "Generic Attribute");
		attributes.put(LINK_LOSS, "Link Loss");
		attributes.put(TX_POWER, "NORDIC_UART_TX Power");
		attributes.put(IMMEDIATE_ALERT, "Immediate Alert");
		attributes.put(NORDIC_UART, "Nordic UART");


		// Heart Rate Characteristics:
		attributes.put(HEART_RATE_MEASUREMENT, "Heart Rate Measurement");
		attributes.put(BODY_SENSOR_LOCATION, "Body Sensor Location");
		attributes.put(HEART_RATE_CONTROL_POINT, "Heart Rate Control Point");


		// Device Information Characteristics:
		attributes.put(MANUFACTURER_NAME_STRING, "Manufacturer Name String");
		attributes.put(HARDWARE_REVISION_STRING, "Hardware Revision String");
		attributes.put(MODEL_NUMBER_STRING, "Model Number String");
		attributes.put(IEEE_11073_20601_REGULATORY_CERTIFICATION_DATA_LIST, "IEEE 11073-20601 Regulatory Certification Data List");
		attributes.put(PNP_ID, "PNP ID");
		attributes.put(SOFTWARE_REVISION_STRING, "Software Revision String");
		attributes.put(SERIAL_NUMBER_STRING, "Serial Number String");
		attributes.put(FIRMWARE_REVISION_STRING, "Firmware Revision String");
		attributes.put(SYSTEM_ID, "System ID");

		// Generic Access Characteristics:
		attributes.put(DEVICE_NAME, "Device Name");
		attributes.put(APPEARANCE, "Appearance");
		attributes.put(PERIPHERAL_PREFERRED_CONNECTION_PARAMETERS, "Peripheral Preferred Connection Parameters");

		// Generic Attribute Characteristics:
		attributes.put(SERVICE_CHANGED, "Service Changed");

		// Battery Service Characteristics:
		attributes.put(BATTERY_LEVEL, "Battery Level");

		// Current Time Service Characteristics:
		attributes.put(CURRENT_TIME, "Current Time");
		attributes.put(LOCAL_TIME_INFORMATION, "Local Time Information");

		// Health Thermometer Characteristics:
		attributes.put(INTERMEDIATE_TEMPERATURE, "Intermediate Temperature");
		attributes.put(TEMPERATURE_MEASUREMENT, "Temperature Measurement");
		attributes.put(TEMPERATURE_TYPE, "Temperature Type");
		attributes.put(MEASUREMENT_INTERVAL, "Measurement Interval");

		// Link Loss Characteristics:
		attributes.put(ALERT_LEVEL, "Alert Level");

		// NORDIC_UART_TX Power Characteristics:
		attributes.put(TX_POWER_LEVEL, "NORDIC_UART_TX Power Level");

		// Nordic UART Characteristics:
		attributes.put(NORDIC_UART_TX, "NORDIC_UART_TX");
		attributes.put(NORDIC_UART_RX, "NORDIC_UART_RX");
	}

	public static String getInfo(String uuid, String defaultName) {
		String result = attributes.get(uuid);
		return result == null ? defaultName : result;
	}
}
