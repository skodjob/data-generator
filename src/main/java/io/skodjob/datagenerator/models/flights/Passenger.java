/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package io.skodjob.datagenerator.models.flights;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class Passenger extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 7514441306189719528L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Passenger\",\"namespace\":\"io.skodjob.datagenerator.models.flights\",\"fields\":[{\"name\":\"id\",\"type\":\"string\"},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"passport_number\",\"type\":\"string\"},{\"name\":\"nationality\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Passenger> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Passenger> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Passenger> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Passenger> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Passenger> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Passenger to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Passenger from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Passenger instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Passenger fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.CharSequence id;
  private java.lang.CharSequence name;
  private java.lang.CharSequence passport_number;
  private java.lang.CharSequence nationality;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Passenger() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param name The new value for name
   * @param passport_number The new value for passport_number
   * @param nationality The new value for nationality
   */
  public Passenger(java.lang.CharSequence id, java.lang.CharSequence name, java.lang.CharSequence passport_number, java.lang.CharSequence nationality) {
    this.id = id;
    this.name = name;
    this.passport_number = passport_number;
    this.nationality = nationality;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return name;
    case 2: return passport_number;
    case 3: return nationality;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.CharSequence)value$; break;
    case 1: name = (java.lang.CharSequence)value$; break;
    case 2: passport_number = (java.lang.CharSequence)value$; break;
    case 3: nationality = (java.lang.CharSequence)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public java.lang.CharSequence getId() {
    return id;
  }


  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.CharSequence value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'name' field.
   * @return The value of the 'name' field.
   */
  public java.lang.CharSequence getName() {
    return name;
  }


  /**
   * Sets the value of the 'name' field.
   * @param value the value to set.
   */
  public void setName(java.lang.CharSequence value) {
    this.name = value;
  }

  /**
   * Gets the value of the 'passport_number' field.
   * @return The value of the 'passport_number' field.
   */
  public java.lang.CharSequence getPassportNumber() {
    return passport_number;
  }


  /**
   * Sets the value of the 'passport_number' field.
   * @param value the value to set.
   */
  public void setPassportNumber(java.lang.CharSequence value) {
    this.passport_number = value;
  }

  /**
   * Gets the value of the 'nationality' field.
   * @return The value of the 'nationality' field.
   */
  public java.lang.CharSequence getNationality() {
    return nationality;
  }


  /**
   * Sets the value of the 'nationality' field.
   * @param value the value to set.
   */
  public void setNationality(java.lang.CharSequence value) {
    this.nationality = value;
  }

  /**
   * Creates a new Passenger RecordBuilder.
   * @return A new Passenger RecordBuilder
   */
  public static io.skodjob.datagenerator.models.flights.Passenger.Builder newBuilder() {
    return new io.skodjob.datagenerator.models.flights.Passenger.Builder();
  }

  /**
   * Creates a new Passenger RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Passenger RecordBuilder
   */
  public static io.skodjob.datagenerator.models.flights.Passenger.Builder newBuilder(io.skodjob.datagenerator.models.flights.Passenger.Builder other) {
    if (other == null) {
      return new io.skodjob.datagenerator.models.flights.Passenger.Builder();
    } else {
      return new io.skodjob.datagenerator.models.flights.Passenger.Builder(other);
    }
  }

  /**
   * Creates a new Passenger RecordBuilder by copying an existing Passenger instance.
   * @param other The existing instance to copy.
   * @return A new Passenger RecordBuilder
   */
  public static io.skodjob.datagenerator.models.flights.Passenger.Builder newBuilder(io.skodjob.datagenerator.models.flights.Passenger other) {
    if (other == null) {
      return new io.skodjob.datagenerator.models.flights.Passenger.Builder();
    } else {
      return new io.skodjob.datagenerator.models.flights.Passenger.Builder(other);
    }
  }

  /**
   * RecordBuilder for Passenger instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Passenger>
    implements org.apache.avro.data.RecordBuilder<Passenger> {

    private java.lang.CharSequence id;
    private java.lang.CharSequence name;
    private java.lang.CharSequence passport_number;
    private java.lang.CharSequence nationality;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(io.skodjob.datagenerator.models.flights.Passenger.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.name)) {
        this.name = data().deepCopy(fields()[1].schema(), other.name);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.passport_number)) {
        this.passport_number = data().deepCopy(fields()[2].schema(), other.passport_number);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.nationality)) {
        this.nationality = data().deepCopy(fields()[3].schema(), other.nationality);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
    }

    /**
     * Creates a Builder by copying an existing Passenger instance
     * @param other The existing instance to copy.
     */
    private Builder(io.skodjob.datagenerator.models.flights.Passenger other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.name)) {
        this.name = data().deepCopy(fields()[1].schema(), other.name);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.passport_number)) {
        this.passport_number = data().deepCopy(fields()[2].schema(), other.passport_number);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.nationality)) {
        this.nationality = data().deepCopy(fields()[3].schema(), other.nationality);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.CharSequence getId() {
      return id;
    }


    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.flights.Passenger.Builder setId(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.flights.Passenger.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'name' field.
      * @return The value.
      */
    public java.lang.CharSequence getName() {
      return name;
    }


    /**
      * Sets the value of the 'name' field.
      * @param value The value of 'name'.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.flights.Passenger.Builder setName(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.name = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'name' field has been set.
      * @return True if the 'name' field has been set, false otherwise.
      */
    public boolean hasName() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'name' field.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.flights.Passenger.Builder clearName() {
      name = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'passport_number' field.
      * @return The value.
      */
    public java.lang.CharSequence getPassportNumber() {
      return passport_number;
    }


    /**
      * Sets the value of the 'passport_number' field.
      * @param value The value of 'passport_number'.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.flights.Passenger.Builder setPassportNumber(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.passport_number = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'passport_number' field has been set.
      * @return True if the 'passport_number' field has been set, false otherwise.
      */
    public boolean hasPassportNumber() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'passport_number' field.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.flights.Passenger.Builder clearPassportNumber() {
      passport_number = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'nationality' field.
      * @return The value.
      */
    public java.lang.CharSequence getNationality() {
      return nationality;
    }


    /**
      * Sets the value of the 'nationality' field.
      * @param value The value of 'nationality'.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.flights.Passenger.Builder setNationality(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.nationality = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'nationality' field has been set.
      * @return True if the 'nationality' field has been set, false otherwise.
      */
    public boolean hasNationality() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'nationality' field.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.flights.Passenger.Builder clearNationality() {
      nationality = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Passenger build() {
      try {
        Passenger record = new Passenger();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.name = fieldSetFlags()[1] ? this.name : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.passport_number = fieldSetFlags()[2] ? this.passport_number : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.nationality = fieldSetFlags()[3] ? this.nationality : (java.lang.CharSequence) defaultValue(fields()[3]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Passenger>
    WRITER$ = (org.apache.avro.io.DatumWriter<Passenger>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Passenger>
    READER$ = (org.apache.avro.io.DatumReader<Passenger>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.id);

    out.writeString(this.name);

    out.writeString(this.passport_number);

    out.writeString(this.nationality);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.id = in.readString(this.id instanceof Utf8 ? (Utf8)this.id : null);

      this.name = in.readString(this.name instanceof Utf8 ? (Utf8)this.name : null);

      this.passport_number = in.readString(this.passport_number instanceof Utf8 ? (Utf8)this.passport_number : null);

      this.nationality = in.readString(this.nationality instanceof Utf8 ? (Utf8)this.nationality : null);

    } else {
      for (int i = 0; i < 4; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.id = in.readString(this.id instanceof Utf8 ? (Utf8)this.id : null);
          break;

        case 1:
          this.name = in.readString(this.name instanceof Utf8 ? (Utf8)this.name : null);
          break;

        case 2:
          this.passport_number = in.readString(this.passport_number instanceof Utf8 ? (Utf8)this.passport_number : null);
          break;

        case 3:
          this.nationality = in.readString(this.nationality instanceof Utf8 ? (Utf8)this.nationality : null);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










