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
public class EnergyCurrent extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 3815547361048666636L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"EnergyCurrent\",\"namespace\":\"io.skodjob.datagenerator.models.iotdevice\",\"fields\":[{\"name\":\"state\",\"type\":\"float\"},{\"name\":\"unit\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<EnergyCurrent> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<EnergyCurrent> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<EnergyCurrent> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<EnergyCurrent> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<EnergyCurrent> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this EnergyCurrent to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a EnergyCurrent from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a EnergyCurrent instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static EnergyCurrent fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private float state;
  private java.lang.CharSequence unit;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public EnergyCurrent() {}

  /**
   * All-args constructor.
   * @param state The new value for state
   * @param unit The new value for unit
   */
  public EnergyCurrent(java.lang.Float state, java.lang.CharSequence unit) {
    this.state = state;
    this.unit = unit;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return state;
    case 1: return unit;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: state = (java.lang.Float)value$; break;
    case 1: unit = (java.lang.CharSequence)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'state' field.
   * @return The value of the 'state' field.
   */
  public float getState() {
    return state;
  }


  /**
   * Sets the value of the 'state' field.
   * @param value the value to set.
   */
  public void setState(float value) {
    this.state = value;
  }

  /**
   * Gets the value of the 'unit' field.
   * @return The value of the 'unit' field.
   */
  public java.lang.CharSequence getUnit() {
    return unit;
  }


  /**
   * Sets the value of the 'unit' field.
   * @param value the value to set.
   */
  public void setUnit(java.lang.CharSequence value) {
    this.unit = value;
  }

  /**
   * Creates a new EnergyCurrent RecordBuilder.
   * @return A new EnergyCurrent RecordBuilder
   */
  public static io.skodjob.datagenerator.models.iotdevice.EnergyCurrent.Builder newBuilder() {
    return new io.skodjob.datagenerator.models.iotdevice.EnergyCurrent.Builder();
  }

  /**
   * Creates a new EnergyCurrent RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new EnergyCurrent RecordBuilder
   */
  public static io.skodjob.datagenerator.models.iotdevice.EnergyCurrent.Builder newBuilder(io.skodjob.datagenerator.models.iotdevice.EnergyCurrent.Builder other) {
    if (other == null) {
      return new io.skodjob.datagenerator.models.iotdevice.EnergyCurrent.Builder();
    } else {
      return new io.skodjob.datagenerator.models.iotdevice.EnergyCurrent.Builder(other);
    }
  }

  /**
   * Creates a new EnergyCurrent RecordBuilder by copying an existing EnergyCurrent instance.
   * @param other The existing instance to copy.
   * @return A new EnergyCurrent RecordBuilder
   */
  public static io.skodjob.datagenerator.models.iotdevice.EnergyCurrent.Builder newBuilder(io.skodjob.datagenerator.models.iotdevice.EnergyCurrent other) {
    if (other == null) {
      return new io.skodjob.datagenerator.models.iotdevice.EnergyCurrent.Builder();
    } else {
      return new io.skodjob.datagenerator.models.iotdevice.EnergyCurrent.Builder(other);
    }
  }

  /**
   * RecordBuilder for EnergyCurrent instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<EnergyCurrent>
    implements org.apache.avro.data.RecordBuilder<EnergyCurrent> {

    private float state;
    private java.lang.CharSequence unit;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(io.skodjob.datagenerator.models.iotdevice.EnergyCurrent.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.state)) {
        this.state = data().deepCopy(fields()[0].schema(), other.state);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.unit)) {
        this.unit = data().deepCopy(fields()[1].schema(), other.unit);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
    }

    /**
     * Creates a Builder by copying an existing EnergyCurrent instance
     * @param other The existing instance to copy.
     */
    private Builder(io.skodjob.datagenerator.models.iotdevice.EnergyCurrent other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.state)) {
        this.state = data().deepCopy(fields()[0].schema(), other.state);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.unit)) {
        this.unit = data().deepCopy(fields()[1].schema(), other.unit);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'state' field.
      * @return The value.
      */
    public float getState() {
      return state;
    }


    /**
      * Sets the value of the 'state' field.
      * @param value The value of 'state'.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.iotdevice.EnergyCurrent.Builder setState(float value) {
      validate(fields()[0], value);
      this.state = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'state' field has been set.
      * @return True if the 'state' field has been set, false otherwise.
      */
    public boolean hasState() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'state' field.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.iotdevice.EnergyCurrent.Builder clearState() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'unit' field.
      * @return The value.
      */
    public java.lang.CharSequence getUnit() {
      return unit;
    }


    /**
      * Sets the value of the 'unit' field.
      * @param value The value of 'unit'.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.iotdevice.EnergyCurrent.Builder setUnit(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.unit = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'unit' field has been set.
      * @return True if the 'unit' field has been set, false otherwise.
      */
    public boolean hasUnit() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'unit' field.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.iotdevice.EnergyCurrent.Builder clearUnit() {
      unit = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public EnergyCurrent build() {
      try {
        EnergyCurrent record = new EnergyCurrent();
        record.state = fieldSetFlags()[0] ? this.state : (java.lang.Float) defaultValue(fields()[0]);
        record.unit = fieldSetFlags()[1] ? this.unit : (java.lang.CharSequence) defaultValue(fields()[1]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<EnergyCurrent>
    WRITER$ = (org.apache.avro.io.DatumWriter<EnergyCurrent>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<EnergyCurrent>
    READER$ = (org.apache.avro.io.DatumReader<EnergyCurrent>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeFloat(this.state);

    out.writeString(this.unit);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.state = in.readFloat();

      this.unit = in.readString(this.unit instanceof Utf8 ? (Utf8)this.unit : null);

    } else {
      for (int i = 0; i < 2; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.state = in.readFloat();
          break;

        case 1:
          this.unit = in.readString(this.unit instanceof Utf8 ? (Utf8)this.unit : null);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










