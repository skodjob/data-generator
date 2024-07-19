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
public class CustomData extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 5873049232784909190L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"CustomData\",\"namespace\":\"io.skodjob.datagenerator.models.iotdevice\",\"fields\":[{\"name\":\"info\",\"type\":\"string\"},{\"name\":\"state\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<CustomData> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<CustomData> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<CustomData> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<CustomData> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<CustomData> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this CustomData to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a CustomData from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a CustomData instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static CustomData fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.CharSequence info;
  private java.lang.CharSequence state;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public CustomData() {}

  /**
   * All-args constructor.
   * @param info The new value for info
   * @param state The new value for state
   */
  public CustomData(java.lang.CharSequence info, java.lang.CharSequence state) {
    this.info = info;
    this.state = state;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return info;
    case 1: return state;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: info = (java.lang.CharSequence)value$; break;
    case 1: state = (java.lang.CharSequence)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'info' field.
   * @return The value of the 'info' field.
   */
  public java.lang.CharSequence getInfo() {
    return info;
  }


  /**
   * Sets the value of the 'info' field.
   * @param value the value to set.
   */
  public void setInfo(java.lang.CharSequence value) {
    this.info = value;
  }

  /**
   * Gets the value of the 'state' field.
   * @return The value of the 'state' field.
   */
  public java.lang.CharSequence getState() {
    return state;
  }


  /**
   * Sets the value of the 'state' field.
   * @param value the value to set.
   */
  public void setState(java.lang.CharSequence value) {
    this.state = value;
  }

  /**
   * Creates a new CustomData RecordBuilder.
   * @return A new CustomData RecordBuilder
   */
  public static io.skodjob.datagenerator.models.iotdevice.CustomData.Builder newBuilder() {
    return new io.skodjob.datagenerator.models.iotdevice.CustomData.Builder();
  }

  /**
   * Creates a new CustomData RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new CustomData RecordBuilder
   */
  public static io.skodjob.datagenerator.models.iotdevice.CustomData.Builder newBuilder(io.skodjob.datagenerator.models.iotdevice.CustomData.Builder other) {
    if (other == null) {
      return new io.skodjob.datagenerator.models.iotdevice.CustomData.Builder();
    } else {
      return new io.skodjob.datagenerator.models.iotdevice.CustomData.Builder(other);
    }
  }

  /**
   * Creates a new CustomData RecordBuilder by copying an existing CustomData instance.
   * @param other The existing instance to copy.
   * @return A new CustomData RecordBuilder
   */
  public static io.skodjob.datagenerator.models.iotdevice.CustomData.Builder newBuilder(io.skodjob.datagenerator.models.iotdevice.CustomData other) {
    if (other == null) {
      return new io.skodjob.datagenerator.models.iotdevice.CustomData.Builder();
    } else {
      return new io.skodjob.datagenerator.models.iotdevice.CustomData.Builder(other);
    }
  }

  /**
   * RecordBuilder for CustomData instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<CustomData>
    implements org.apache.avro.data.RecordBuilder<CustomData> {

    private java.lang.CharSequence info;
    private java.lang.CharSequence state;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(io.skodjob.datagenerator.models.iotdevice.CustomData.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.info)) {
        this.info = data().deepCopy(fields()[0].schema(), other.info);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.state)) {
        this.state = data().deepCopy(fields()[1].schema(), other.state);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
    }

    /**
     * Creates a Builder by copying an existing CustomData instance
     * @param other The existing instance to copy.
     */
    private Builder(io.skodjob.datagenerator.models.iotdevice.CustomData other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.info)) {
        this.info = data().deepCopy(fields()[0].schema(), other.info);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.state)) {
        this.state = data().deepCopy(fields()[1].schema(), other.state);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'info' field.
      * @return The value.
      */
    public java.lang.CharSequence getInfo() {
      return info;
    }


    /**
      * Sets the value of the 'info' field.
      * @param value The value of 'info'.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.iotdevice.CustomData.Builder setInfo(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.info = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'info' field has been set.
      * @return True if the 'info' field has been set, false otherwise.
      */
    public boolean hasInfo() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'info' field.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.iotdevice.CustomData.Builder clearInfo() {
      info = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'state' field.
      * @return The value.
      */
    public java.lang.CharSequence getState() {
      return state;
    }


    /**
      * Sets the value of the 'state' field.
      * @param value The value of 'state'.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.iotdevice.CustomData.Builder setState(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.state = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'state' field has been set.
      * @return True if the 'state' field has been set, false otherwise.
      */
    public boolean hasState() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'state' field.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.iotdevice.CustomData.Builder clearState() {
      state = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public CustomData build() {
      try {
        CustomData record = new CustomData();
        record.info = fieldSetFlags()[0] ? this.info : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.state = fieldSetFlags()[1] ? this.state : (java.lang.CharSequence) defaultValue(fields()[1]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<CustomData>
    WRITER$ = (org.apache.avro.io.DatumWriter<CustomData>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<CustomData>
    READER$ = (org.apache.avro.io.DatumReader<CustomData>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.info);

    out.writeString(this.state);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.info = in.readString(this.info instanceof Utf8 ? (Utf8)this.info : null);

      this.state = in.readString(this.state instanceof Utf8 ? (Utf8)this.state : null);

    } else {
      for (int i = 0; i < 2; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.info = in.readString(this.info instanceof Utf8 ? (Utf8)this.info : null);
          break;

        case 1:
          this.state = in.readString(this.state instanceof Utf8 ? (Utf8)this.state : null);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










