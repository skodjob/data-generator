/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package io.skodjob.datagenerator.models.iotdevice;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class IotDevice extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -6035530976238410191L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"IotDevice\",\"namespace\":\"io.skodjob.datagenerator.models.iotdevice\",\"fields\":[{\"name\":\"IPV4\",\"type\":\"string\"},{\"name\":\"MAC\",\"type\":\"string\"},{\"name\":\"ID\",\"type\":\"string\"},{\"name\":\"TYPE\",\"type\":\"string\"},{\"name\":\"LAST_UPDATE\",\"type\":\"string\"},{\"name\":\"LINK_QUALITY\",\"type\":\"int\"},{\"name\":\"DATA\",\"type\":[{\"type\":\"record\",\"name\":\"CustomData\",\"fields\":[{\"name\":\"info\",\"type\":\"string\"},{\"name\":\"state\",\"type\":\"string\"}]},{\"type\":\"record\",\"name\":\"ButtonData\",\"fields\":[{\"name\":\"power\",\"type\":\"string\"},{\"name\":\"battery\",\"type\":{\"type\":\"record\",\"name\":\"Battery\",\"fields\":[{\"name\":\"value\",\"type\":\"int\"},{\"name\":\"unit\",\"type\":\"string\"}]}}]},{\"type\":\"record\",\"name\":\"PlugData\",\"fields\":[{\"name\":\"power\",\"type\":\"string\"},{\"name\":\"energy_current\",\"type\":{\"type\":\"record\",\"name\":\"EnergyCurrent\",\"fields\":[{\"name\":\"state\",\"type\":\"float\"},{\"name\":\"unit\",\"type\":\"string\"}]}},{\"name\":\"energy_today\",\"type\":{\"type\":\"record\",\"name\":\"EnergyToday\",\"fields\":[{\"name\":\"state\",\"type\":\"float\"},{\"name\":\"unit\",\"type\":\"string\"}]}}]},{\"type\":\"record\",\"name\":\"ThermometerData\",\"fields\":[{\"name\":\"temperature\",\"type\":\"float\"},{\"name\":\"humidity\",\"type\":\"float\"},{\"name\":\"battery\",\"type\":\"Battery\"}]},{\"type\":\"record\",\"name\":\"GateData\",\"fields\":[{\"name\":\"vendor\",\"type\":\"string\"},{\"name\":\"state\",\"type\":\"string\"}]},{\"type\":\"record\",\"name\":\"LightData\",\"fields\":[{\"name\":\"power\",\"type\":\"string\"},{\"name\":\"brightness\",\"type\":\"int\"},{\"name\":\"power_on_behavior\",\"type\":\"string\"}]}]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<IotDevice> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<IotDevice> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<IotDevice> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<IotDevice> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<IotDevice> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this IotDevice to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a IotDevice from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a IotDevice instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static IotDevice fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.CharSequence IPV4;
  private java.lang.CharSequence MAC;
  private java.lang.CharSequence ID;
  private java.lang.CharSequence TYPE;
  private java.lang.CharSequence LAST_UPDATE;
  private int LINK_QUALITY;
  private java.lang.Object DATA;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public IotDevice() {}

  /**
   * All-args constructor.
   * @param IPV4 The new value for IPV4
   * @param MAC The new value for MAC
   * @param ID The new value for ID
   * @param TYPE The new value for TYPE
   * @param LAST_UPDATE The new value for LAST_UPDATE
   * @param LINK_QUALITY The new value for LINK_QUALITY
   * @param DATA The new value for DATA
   */
  public IotDevice(java.lang.CharSequence IPV4, java.lang.CharSequence MAC, java.lang.CharSequence ID, java.lang.CharSequence TYPE, java.lang.CharSequence LAST_UPDATE, java.lang.Integer LINK_QUALITY, java.lang.Object DATA) {
    this.IPV4 = IPV4;
    this.MAC = MAC;
    this.ID = ID;
    this.TYPE = TYPE;
    this.LAST_UPDATE = LAST_UPDATE;
    this.LINK_QUALITY = LINK_QUALITY;
    this.DATA = DATA;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return IPV4;
    case 1: return MAC;
    case 2: return ID;
    case 3: return TYPE;
    case 4: return LAST_UPDATE;
    case 5: return LINK_QUALITY;
    case 6: return DATA;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: IPV4 = (java.lang.CharSequence)value$; break;
    case 1: MAC = (java.lang.CharSequence)value$; break;
    case 2: ID = (java.lang.CharSequence)value$; break;
    case 3: TYPE = (java.lang.CharSequence)value$; break;
    case 4: LAST_UPDATE = (java.lang.CharSequence)value$; break;
    case 5: LINK_QUALITY = (java.lang.Integer)value$; break;
    case 6: DATA = value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'IPV4' field.
   * @return The value of the 'IPV4' field.
   */
  public java.lang.CharSequence getIPV4() {
    return IPV4;
  }


  /**
   * Sets the value of the 'IPV4' field.
   * @param value the value to set.
   */
  public void setIPV4(java.lang.CharSequence value) {
    this.IPV4 = value;
  }

  /**
   * Gets the value of the 'MAC' field.
   * @return The value of the 'MAC' field.
   */
  public java.lang.CharSequence getMAC() {
    return MAC;
  }


  /**
   * Sets the value of the 'MAC' field.
   * @param value the value to set.
   */
  public void setMAC(java.lang.CharSequence value) {
    this.MAC = value;
  }

  /**
   * Gets the value of the 'ID' field.
   * @return The value of the 'ID' field.
   */
  public java.lang.CharSequence getID() {
    return ID;
  }


  /**
   * Sets the value of the 'ID' field.
   * @param value the value to set.
   */
  public void setID(java.lang.CharSequence value) {
    this.ID = value;
  }

  /**
   * Gets the value of the 'TYPE' field.
   * @return The value of the 'TYPE' field.
   */
  public java.lang.CharSequence getTYPE() {
    return TYPE;
  }


  /**
   * Sets the value of the 'TYPE' field.
   * @param value the value to set.
   */
  public void setTYPE(java.lang.CharSequence value) {
    this.TYPE = value;
  }

  /**
   * Gets the value of the 'LAST_UPDATE' field.
   * @return The value of the 'LAST_UPDATE' field.
   */
  public java.lang.CharSequence getLASTUPDATE() {
    return LAST_UPDATE;
  }


  /**
   * Sets the value of the 'LAST_UPDATE' field.
   * @param value the value to set.
   */
  public void setLASTUPDATE(java.lang.CharSequence value) {
    this.LAST_UPDATE = value;
  }

  /**
   * Gets the value of the 'LINK_QUALITY' field.
   * @return The value of the 'LINK_QUALITY' field.
   */
  public int getLINKQUALITY() {
    return LINK_QUALITY;
  }


  /**
   * Sets the value of the 'LINK_QUALITY' field.
   * @param value the value to set.
   */
  public void setLINKQUALITY(int value) {
    this.LINK_QUALITY = value;
  }

  /**
   * Gets the value of the 'DATA' field.
   * @return The value of the 'DATA' field.
   */
  public java.lang.Object getDATA() {
    return DATA;
  }


  /**
   * Sets the value of the 'DATA' field.
   * @param value the value to set.
   */
  public void setDATA(java.lang.Object value) {
    this.DATA = value;
  }

  /**
   * Creates a new IotDevice RecordBuilder.
   * @return A new IotDevice RecordBuilder
   */
  public static io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder newBuilder() {
    return new io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder();
  }

  /**
   * Creates a new IotDevice RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new IotDevice RecordBuilder
   */
  public static io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder newBuilder(io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder other) {
    if (other == null) {
      return new io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder();
    } else {
      return new io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder(other);
    }
  }

  /**
   * Creates a new IotDevice RecordBuilder by copying an existing IotDevice instance.
   * @param other The existing instance to copy.
   * @return A new IotDevice RecordBuilder
   */
  public static io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder newBuilder(io.skodjob.datagenerator.models.iotdevice.IotDevice other) {
    if (other == null) {
      return new io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder();
    } else {
      return new io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder(other);
    }
  }

  /**
   * RecordBuilder for IotDevice instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<IotDevice>
    implements org.apache.avro.data.RecordBuilder<IotDevice> {

    private java.lang.CharSequence IPV4;
    private java.lang.CharSequence MAC;
    private java.lang.CharSequence ID;
    private java.lang.CharSequence TYPE;
    private java.lang.CharSequence LAST_UPDATE;
    private int LINK_QUALITY;
    private java.lang.Object DATA;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.IPV4)) {
        this.IPV4 = data().deepCopy(fields()[0].schema(), other.IPV4);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.MAC)) {
        this.MAC = data().deepCopy(fields()[1].schema(), other.MAC);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.ID)) {
        this.ID = data().deepCopy(fields()[2].schema(), other.ID);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.TYPE)) {
        this.TYPE = data().deepCopy(fields()[3].schema(), other.TYPE);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.LAST_UPDATE)) {
        this.LAST_UPDATE = data().deepCopy(fields()[4].schema(), other.LAST_UPDATE);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.LINK_QUALITY)) {
        this.LINK_QUALITY = data().deepCopy(fields()[5].schema(), other.LINK_QUALITY);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.DATA)) {
        this.DATA = data().deepCopy(fields()[6].schema(), other.DATA);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
    }

    /**
     * Creates a Builder by copying an existing IotDevice instance
     * @param other The existing instance to copy.
     */
    private Builder(io.skodjob.datagenerator.models.iotdevice.IotDevice other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.IPV4)) {
        this.IPV4 = data().deepCopy(fields()[0].schema(), other.IPV4);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.MAC)) {
        this.MAC = data().deepCopy(fields()[1].schema(), other.MAC);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.ID)) {
        this.ID = data().deepCopy(fields()[2].schema(), other.ID);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.TYPE)) {
        this.TYPE = data().deepCopy(fields()[3].schema(), other.TYPE);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.LAST_UPDATE)) {
        this.LAST_UPDATE = data().deepCopy(fields()[4].schema(), other.LAST_UPDATE);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.LINK_QUALITY)) {
        this.LINK_QUALITY = data().deepCopy(fields()[5].schema(), other.LINK_QUALITY);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.DATA)) {
        this.DATA = data().deepCopy(fields()[6].schema(), other.DATA);
        fieldSetFlags()[6] = true;
      }
    }

    /**
      * Gets the value of the 'IPV4' field.
      * @return The value.
      */
    public java.lang.CharSequence getIPV4() {
      return IPV4;
    }


    /**
      * Sets the value of the 'IPV4' field.
      * @param value The value of 'IPV4'.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder setIPV4(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.IPV4 = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'IPV4' field has been set.
      * @return True if the 'IPV4' field has been set, false otherwise.
      */
    public boolean hasIPV4() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'IPV4' field.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder clearIPV4() {
      IPV4 = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'MAC' field.
      * @return The value.
      */
    public java.lang.CharSequence getMAC() {
      return MAC;
    }


    /**
      * Sets the value of the 'MAC' field.
      * @param value The value of 'MAC'.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder setMAC(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.MAC = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'MAC' field has been set.
      * @return True if the 'MAC' field has been set, false otherwise.
      */
    public boolean hasMAC() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'MAC' field.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder clearMAC() {
      MAC = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'ID' field.
      * @return The value.
      */
    public java.lang.CharSequence getID() {
      return ID;
    }


    /**
      * Sets the value of the 'ID' field.
      * @param value The value of 'ID'.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder setID(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.ID = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'ID' field has been set.
      * @return True if the 'ID' field has been set, false otherwise.
      */
    public boolean hasID() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'ID' field.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder clearID() {
      ID = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'TYPE' field.
      * @return The value.
      */
    public java.lang.CharSequence getTYPE() {
      return TYPE;
    }


    /**
      * Sets the value of the 'TYPE' field.
      * @param value The value of 'TYPE'.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder setTYPE(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.TYPE = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'TYPE' field has been set.
      * @return True if the 'TYPE' field has been set, false otherwise.
      */
    public boolean hasTYPE() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'TYPE' field.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder clearTYPE() {
      TYPE = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'LAST_UPDATE' field.
      * @return The value.
      */
    public java.lang.CharSequence getLASTUPDATE() {
      return LAST_UPDATE;
    }


    /**
      * Sets the value of the 'LAST_UPDATE' field.
      * @param value The value of 'LAST_UPDATE'.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder setLASTUPDATE(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.LAST_UPDATE = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'LAST_UPDATE' field has been set.
      * @return True if the 'LAST_UPDATE' field has been set, false otherwise.
      */
    public boolean hasLASTUPDATE() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'LAST_UPDATE' field.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder clearLASTUPDATE() {
      LAST_UPDATE = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'LINK_QUALITY' field.
      * @return The value.
      */
    public int getLINKQUALITY() {
      return LINK_QUALITY;
    }


    /**
      * Sets the value of the 'LINK_QUALITY' field.
      * @param value The value of 'LINK_QUALITY'.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder setLINKQUALITY(int value) {
      validate(fields()[5], value);
      this.LINK_QUALITY = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'LINK_QUALITY' field has been set.
      * @return True if the 'LINK_QUALITY' field has been set, false otherwise.
      */
    public boolean hasLINKQUALITY() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'LINK_QUALITY' field.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder clearLINKQUALITY() {
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'DATA' field.
      * @return The value.
      */
    public java.lang.Object getDATA() {
      return DATA;
    }


    /**
      * Sets the value of the 'DATA' field.
      * @param value The value of 'DATA'.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder setDATA(java.lang.Object value) {
      validate(fields()[6], value);
      this.DATA = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'DATA' field has been set.
      * @return True if the 'DATA' field has been set, false otherwise.
      */
    public boolean hasDATA() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'DATA' field.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.iotdevice.IotDevice.Builder clearDATA() {
      DATA = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public IotDevice build() {
      try {
        IotDevice record = new IotDevice();
        record.IPV4 = fieldSetFlags()[0] ? this.IPV4 : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.MAC = fieldSetFlags()[1] ? this.MAC : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.ID = fieldSetFlags()[2] ? this.ID : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.TYPE = fieldSetFlags()[3] ? this.TYPE : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.LAST_UPDATE = fieldSetFlags()[4] ? this.LAST_UPDATE : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.LINK_QUALITY = fieldSetFlags()[5] ? this.LINK_QUALITY : (java.lang.Integer) defaultValue(fields()[5]);
        record.DATA = fieldSetFlags()[6] ? this.DATA :  defaultValue(fields()[6]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<IotDevice>
    WRITER$ = (org.apache.avro.io.DatumWriter<IotDevice>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<IotDevice>
    READER$ = (org.apache.avro.io.DatumReader<IotDevice>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}










