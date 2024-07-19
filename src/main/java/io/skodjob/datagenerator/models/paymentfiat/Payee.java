/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package io.skodjob.datagenerator.models.paymentfiat;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class Payee extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -4078330169720103566L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Payee\",\"namespace\":\"io.skodjob.datagenerator.models\",\"fields\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"payeeType\",\"type\":\"string\"},{\"name\":\"accountNumber\",\"type\":\"string\"},{\"name\":\"bank\",\"type\":\"string\"},{\"name\":\"address\",\"type\":{\"type\":\"record\",\"name\":\"Address\",\"fields\":[{\"name\":\"street\",\"type\":\"string\"},{\"name\":\"city\",\"type\":\"string\"},{\"name\":\"state\",\"type\":\"string\"},{\"name\":\"country\",\"type\":\"string\"},{\"name\":\"postalCode\",\"type\":\"string\"}]}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Payee> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Payee> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Payee> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Payee> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Payee> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Payee to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Payee from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Payee instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Payee fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.CharSequence name;
  private java.lang.CharSequence payeeType;
  private java.lang.CharSequence accountNumber;
  private java.lang.CharSequence bank;
  private Address address;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Payee() {}

  /**
   * All-args constructor.
   * @param name The new value for name
   * @param payeeType The new value for payeeType
   * @param accountNumber The new value for accountNumber
   * @param bank The new value for bank
   * @param address The new value for address
   */
  public Payee(java.lang.CharSequence name, java.lang.CharSequence payeeType, java.lang.CharSequence accountNumber, java.lang.CharSequence bank, Address address) {
    this.name = name;
    this.payeeType = payeeType;
    this.accountNumber = accountNumber;
    this.bank = bank;
    this.address = address;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return name;
    case 1: return payeeType;
    case 2: return accountNumber;
    case 3: return bank;
    case 4: return address;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: name = (java.lang.CharSequence)value$; break;
    case 1: payeeType = (java.lang.CharSequence)value$; break;
    case 2: accountNumber = (java.lang.CharSequence)value$; break;
    case 3: bank = (java.lang.CharSequence)value$; break;
    case 4: address = (Address)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
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
   * Gets the value of the 'payeeType' field.
   * @return The value of the 'payeeType' field.
   */
  public java.lang.CharSequence getPayeeType() {
    return payeeType;
  }


  /**
   * Sets the value of the 'payeeType' field.
   * @param value the value to set.
   */
  public void setPayeeType(java.lang.CharSequence value) {
    this.payeeType = value;
  }

  /**
   * Gets the value of the 'accountNumber' field.
   * @return The value of the 'accountNumber' field.
   */
  public java.lang.CharSequence getAccountNumber() {
    return accountNumber;
  }


  /**
   * Sets the value of the 'accountNumber' field.
   * @param value the value to set.
   */
  public void setAccountNumber(java.lang.CharSequence value) {
    this.accountNumber = value;
  }

  /**
   * Gets the value of the 'bank' field.
   * @return The value of the 'bank' field.
   */
  public java.lang.CharSequence getBank() {
    return bank;
  }


  /**
   * Sets the value of the 'bank' field.
   * @param value the value to set.
   */
  public void setBank(java.lang.CharSequence value) {
    this.bank = value;
  }

  /**
   * Gets the value of the 'address' field.
   * @return The value of the 'address' field.
   */
  public Address getAddress() {
    return address;
  }


  /**
   * Sets the value of the 'address' field.
   * @param value the value to set.
   */
  public void setAddress(Address value) {
    this.address = value;
  }

  /**
   * Creates a new Payee RecordBuilder.
   * @return A new Payee RecordBuilder
   */
  public static Payee.Builder newBuilder() {
    return new Payee.Builder();
  }

  /**
   * Creates a new Payee RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Payee RecordBuilder
   */
  public static Payee.Builder newBuilder(Payee.Builder other) {
    if (other == null) {
      return new Payee.Builder();
    } else {
      return new Payee.Builder(other);
    }
  }

  /**
   * Creates a new Payee RecordBuilder by copying an existing Payee instance.
   * @param other The existing instance to copy.
   * @return A new Payee RecordBuilder
   */
  public static Payee.Builder newBuilder(Payee other) {
    if (other == null) {
      return new Payee.Builder();
    } else {
      return new Payee.Builder(other);
    }
  }

  /**
   * RecordBuilder for Payee instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Payee>
    implements org.apache.avro.data.RecordBuilder<Payee> {

    private java.lang.CharSequence name;
    private java.lang.CharSequence payeeType;
    private java.lang.CharSequence accountNumber;
    private java.lang.CharSequence bank;
    private Address address;
    private Address.Builder addressBuilder;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(Payee.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.payeeType)) {
        this.payeeType = data().deepCopy(fields()[1].schema(), other.payeeType);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.accountNumber)) {
        this.accountNumber = data().deepCopy(fields()[2].schema(), other.accountNumber);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.bank)) {
        this.bank = data().deepCopy(fields()[3].schema(), other.bank);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.address)) {
        this.address = data().deepCopy(fields()[4].schema(), other.address);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (other.hasAddressBuilder()) {
        this.addressBuilder = Address.newBuilder(other.getAddressBuilder());
      }
    }

    /**
     * Creates a Builder by copying an existing Payee instance
     * @param other The existing instance to copy.
     */
    private Builder(Payee other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.payeeType)) {
        this.payeeType = data().deepCopy(fields()[1].schema(), other.payeeType);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.accountNumber)) {
        this.accountNumber = data().deepCopy(fields()[2].schema(), other.accountNumber);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.bank)) {
        this.bank = data().deepCopy(fields()[3].schema(), other.bank);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.address)) {
        this.address = data().deepCopy(fields()[4].schema(), other.address);
        fieldSetFlags()[4] = true;
      }
      this.addressBuilder = null;
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
    public Payee.Builder setName(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.name = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'name' field has been set.
      * @return True if the 'name' field has been set, false otherwise.
      */
    public boolean hasName() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'name' field.
      * @return This builder.
      */
    public Payee.Builder clearName() {
      name = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'payeeType' field.
      * @return The value.
      */
    public java.lang.CharSequence getPayeeType() {
      return payeeType;
    }


    /**
      * Sets the value of the 'payeeType' field.
      * @param value The value of 'payeeType'.
      * @return This builder.
      */
    public Payee.Builder setPayeeType(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.payeeType = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'payeeType' field has been set.
      * @return True if the 'payeeType' field has been set, false otherwise.
      */
    public boolean hasPayeeType() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'payeeType' field.
      * @return This builder.
      */
    public Payee.Builder clearPayeeType() {
      payeeType = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'accountNumber' field.
      * @return The value.
      */
    public java.lang.CharSequence getAccountNumber() {
      return accountNumber;
    }


    /**
      * Sets the value of the 'accountNumber' field.
      * @param value The value of 'accountNumber'.
      * @return This builder.
      */
    public Payee.Builder setAccountNumber(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.accountNumber = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'accountNumber' field has been set.
      * @return True if the 'accountNumber' field has been set, false otherwise.
      */
    public boolean hasAccountNumber() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'accountNumber' field.
      * @return This builder.
      */
    public Payee.Builder clearAccountNumber() {
      accountNumber = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'bank' field.
      * @return The value.
      */
    public java.lang.CharSequence getBank() {
      return bank;
    }


    /**
      * Sets the value of the 'bank' field.
      * @param value The value of 'bank'.
      * @return This builder.
      */
    public Payee.Builder setBank(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.bank = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'bank' field has been set.
      * @return True if the 'bank' field has been set, false otherwise.
      */
    public boolean hasBank() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'bank' field.
      * @return This builder.
      */
    public Payee.Builder clearBank() {
      bank = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'address' field.
      * @return The value.
      */
    public Address getAddress() {
      return address;
    }


    /**
      * Sets the value of the 'address' field.
      * @param value The value of 'address'.
      * @return This builder.
      */
    public Payee.Builder setAddress(Address value) {
      validate(fields()[4], value);
      this.addressBuilder = null;
      this.address = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'address' field has been set.
      * @return True if the 'address' field has been set, false otherwise.
      */
    public boolean hasAddress() {
      return fieldSetFlags()[4];
    }

    /**
     * Gets the Builder instance for the 'address' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public Address.Builder getAddressBuilder() {
      if (addressBuilder == null) {
        if (hasAddress()) {
          setAddressBuilder(Address.newBuilder(address));
        } else {
          setAddressBuilder(Address.newBuilder());
        }
      }
      return addressBuilder;
    }

    /**
     * Sets the Builder instance for the 'address' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */

    public Payee.Builder setAddressBuilder(Address.Builder value) {
      clearAddress();
      addressBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'address' field has an active Builder instance
     * @return True if the 'address' field has an active Builder instance
     */
    public boolean hasAddressBuilder() {
      return addressBuilder != null;
    }

    /**
      * Clears the value of the 'address' field.
      * @return This builder.
      */
    public Payee.Builder clearAddress() {
      address = null;
      addressBuilder = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Payee build() {
      try {
        Payee record = new Payee();
        record.name = fieldSetFlags()[0] ? this.name : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.payeeType = fieldSetFlags()[1] ? this.payeeType : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.accountNumber = fieldSetFlags()[2] ? this.accountNumber : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.bank = fieldSetFlags()[3] ? this.bank : (java.lang.CharSequence) defaultValue(fields()[3]);
        if (addressBuilder != null) {
          try {
            record.address = this.addressBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("address"));
            throw e;
          }
        } else {
          record.address = fieldSetFlags()[4] ? this.address : (Address) defaultValue(fields()[4]);
        }
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Payee>
    WRITER$ = (org.apache.avro.io.DatumWriter<Payee>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Payee>
    READER$ = (org.apache.avro.io.DatumReader<Payee>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.name);

    out.writeString(this.payeeType);

    out.writeString(this.accountNumber);

    out.writeString(this.bank);

    this.address.customEncode(out);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.name = in.readString(this.name instanceof Utf8 ? (Utf8)this.name : null);

      this.payeeType = in.readString(this.payeeType instanceof Utf8 ? (Utf8)this.payeeType : null);

      this.accountNumber = in.readString(this.accountNumber instanceof Utf8 ? (Utf8)this.accountNumber : null);

      this.bank = in.readString(this.bank instanceof Utf8 ? (Utf8)this.bank : null);

      if (this.address == null) {
        this.address = new Address();
      }
      this.address.customDecode(in);

    } else {
      for (int i = 0; i < 5; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.name = in.readString(this.name instanceof Utf8 ? (Utf8)this.name : null);
          break;

        case 1:
          this.payeeType = in.readString(this.payeeType instanceof Utf8 ? (Utf8)this.payeeType : null);
          break;

        case 2:
          this.accountNumber = in.readString(this.accountNumber instanceof Utf8 ? (Utf8)this.accountNumber : null);
          break;

        case 3:
          this.bank = in.readString(this.bank instanceof Utf8 ? (Utf8)this.bank : null);
          break;

        case 4:
          if (this.address == null) {
            this.address = new Address();
          }
          this.address.customDecode(in);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










