/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package io.skodjob.datagenerator.models.paymentfiat;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class Payer extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 5810398614907498299L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Payer\",\"namespace\":\"io.skodjob.datagenerator.models.paymentfiat\",\"fields\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"payerType\",\"type\":\"string\"},{\"name\":\"accountNumber\",\"type\":\"string\"},{\"name\":\"bank\",\"type\":\"string\"},{\"name\":\"billingAddress\",\"type\":{\"type\":\"record\",\"name\":\"Address\",\"fields\":[{\"name\":\"street\",\"type\":\"string\"},{\"name\":\"city\",\"type\":\"string\"},{\"name\":\"state\",\"type\":\"string\"},{\"name\":\"country\",\"type\":\"string\"},{\"name\":\"postalCode\",\"type\":\"string\"}]}},{\"name\":\"cardNumber\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"cardType\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"expiryDate\",\"type\":[\"null\",\"string\"],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Payer> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Payer> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Payer> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Payer> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Payer> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Payer to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Payer from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Payer instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Payer fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.CharSequence name;
  private java.lang.CharSequence payerType;
  private java.lang.CharSequence accountNumber;
  private java.lang.CharSequence bank;
  private io.skodjob.datagenerator.models.paymentfiat.Address billingAddress;
  private java.lang.CharSequence cardNumber;
  private java.lang.CharSequence cardType;
  private java.lang.CharSequence expiryDate;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Payer() {}

  /**
   * All-args constructor.
   * @param name The new value for name
   * @param payerType The new value for payerType
   * @param accountNumber The new value for accountNumber
   * @param bank The new value for bank
   * @param billingAddress The new value for billingAddress
   * @param cardNumber The new value for cardNumber
   * @param cardType The new value for cardType
   * @param expiryDate The new value for expiryDate
   */
  public Payer(java.lang.CharSequence name, java.lang.CharSequence payerType, java.lang.CharSequence accountNumber, java.lang.CharSequence bank, io.skodjob.datagenerator.models.paymentfiat.Address billingAddress, java.lang.CharSequence cardNumber, java.lang.CharSequence cardType, java.lang.CharSequence expiryDate) {
    this.name = name;
    this.payerType = payerType;
    this.accountNumber = accountNumber;
    this.bank = bank;
    this.billingAddress = billingAddress;
    this.cardNumber = cardNumber;
    this.cardType = cardType;
    this.expiryDate = expiryDate;
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
    case 1: return payerType;
    case 2: return accountNumber;
    case 3: return bank;
    case 4: return billingAddress;
    case 5: return cardNumber;
    case 6: return cardType;
    case 7: return expiryDate;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: name = (java.lang.CharSequence)value$; break;
    case 1: payerType = (java.lang.CharSequence)value$; break;
    case 2: accountNumber = (java.lang.CharSequence)value$; break;
    case 3: bank = (java.lang.CharSequence)value$; break;
    case 4: billingAddress = (io.skodjob.datagenerator.models.paymentfiat.Address)value$; break;
    case 5: cardNumber = (java.lang.CharSequence)value$; break;
    case 6: cardType = (java.lang.CharSequence)value$; break;
    case 7: expiryDate = (java.lang.CharSequence)value$; break;
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
   * Gets the value of the 'payerType' field.
   * @return The value of the 'payerType' field.
   */
  public java.lang.CharSequence getPayerType() {
    return payerType;
  }


  /**
   * Sets the value of the 'payerType' field.
   * @param value the value to set.
   */
  public void setPayerType(java.lang.CharSequence value) {
    this.payerType = value;
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
   * Gets the value of the 'billingAddress' field.
   * @return The value of the 'billingAddress' field.
   */
  public io.skodjob.datagenerator.models.paymentfiat.Address getBillingAddress() {
    return billingAddress;
  }


  /**
   * Sets the value of the 'billingAddress' field.
   * @param value the value to set.
   */
  public void setBillingAddress(io.skodjob.datagenerator.models.paymentfiat.Address value) {
    this.billingAddress = value;
  }

  /**
   * Gets the value of the 'cardNumber' field.
   * @return The value of the 'cardNumber' field.
   */
  public java.lang.CharSequence getCardNumber() {
    return cardNumber;
  }


  /**
   * Sets the value of the 'cardNumber' field.
   * @param value the value to set.
   */
  public void setCardNumber(java.lang.CharSequence value) {
    this.cardNumber = value;
  }

  /**
   * Gets the value of the 'cardType' field.
   * @return The value of the 'cardType' field.
   */
  public java.lang.CharSequence getCardType() {
    return cardType;
  }


  /**
   * Sets the value of the 'cardType' field.
   * @param value the value to set.
   */
  public void setCardType(java.lang.CharSequence value) {
    this.cardType = value;
  }

  /**
   * Gets the value of the 'expiryDate' field.
   * @return The value of the 'expiryDate' field.
   */
  public java.lang.CharSequence getExpiryDate() {
    return expiryDate;
  }


  /**
   * Sets the value of the 'expiryDate' field.
   * @param value the value to set.
   */
  public void setExpiryDate(java.lang.CharSequence value) {
    this.expiryDate = value;
  }

  /**
   * Creates a new Payer RecordBuilder.
   * @return A new Payer RecordBuilder
   */
  public static io.skodjob.datagenerator.models.paymentfiat.Payer.Builder newBuilder() {
    return new io.skodjob.datagenerator.models.paymentfiat.Payer.Builder();
  }

  /**
   * Creates a new Payer RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Payer RecordBuilder
   */
  public static io.skodjob.datagenerator.models.paymentfiat.Payer.Builder newBuilder(io.skodjob.datagenerator.models.paymentfiat.Payer.Builder other) {
    if (other == null) {
      return new io.skodjob.datagenerator.models.paymentfiat.Payer.Builder();
    } else {
      return new io.skodjob.datagenerator.models.paymentfiat.Payer.Builder(other);
    }
  }

  /**
   * Creates a new Payer RecordBuilder by copying an existing Payer instance.
   * @param other The existing instance to copy.
   * @return A new Payer RecordBuilder
   */
  public static io.skodjob.datagenerator.models.paymentfiat.Payer.Builder newBuilder(io.skodjob.datagenerator.models.paymentfiat.Payer other) {
    if (other == null) {
      return new io.skodjob.datagenerator.models.paymentfiat.Payer.Builder();
    } else {
      return new io.skodjob.datagenerator.models.paymentfiat.Payer.Builder(other);
    }
  }

  /**
   * RecordBuilder for Payer instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Payer>
    implements org.apache.avro.data.RecordBuilder<Payer> {

    private java.lang.CharSequence name;
    private java.lang.CharSequence payerType;
    private java.lang.CharSequence accountNumber;
    private java.lang.CharSequence bank;
    private io.skodjob.datagenerator.models.paymentfiat.Address billingAddress;
    private io.skodjob.datagenerator.models.paymentfiat.Address.Builder billingAddressBuilder;
    private java.lang.CharSequence cardNumber;
    private java.lang.CharSequence cardType;
    private java.lang.CharSequence expiryDate;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(io.skodjob.datagenerator.models.paymentfiat.Payer.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.payerType)) {
        this.payerType = data().deepCopy(fields()[1].schema(), other.payerType);
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
      if (isValidValue(fields()[4], other.billingAddress)) {
        this.billingAddress = data().deepCopy(fields()[4].schema(), other.billingAddress);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (other.hasBillingAddressBuilder()) {
        this.billingAddressBuilder = io.skodjob.datagenerator.models.paymentfiat.Address.newBuilder(other.getBillingAddressBuilder());
      }
      if (isValidValue(fields()[5], other.cardNumber)) {
        this.cardNumber = data().deepCopy(fields()[5].schema(), other.cardNumber);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.cardType)) {
        this.cardType = data().deepCopy(fields()[6].schema(), other.cardType);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (isValidValue(fields()[7], other.expiryDate)) {
        this.expiryDate = data().deepCopy(fields()[7].schema(), other.expiryDate);
        fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
    }

    /**
     * Creates a Builder by copying an existing Payer instance
     * @param other The existing instance to copy.
     */
    private Builder(io.skodjob.datagenerator.models.paymentfiat.Payer other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.payerType)) {
        this.payerType = data().deepCopy(fields()[1].schema(), other.payerType);
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
      if (isValidValue(fields()[4], other.billingAddress)) {
        this.billingAddress = data().deepCopy(fields()[4].schema(), other.billingAddress);
        fieldSetFlags()[4] = true;
      }
      this.billingAddressBuilder = null;
      if (isValidValue(fields()[5], other.cardNumber)) {
        this.cardNumber = data().deepCopy(fields()[5].schema(), other.cardNumber);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.cardType)) {
        this.cardType = data().deepCopy(fields()[6].schema(), other.cardType);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.expiryDate)) {
        this.expiryDate = data().deepCopy(fields()[7].schema(), other.expiryDate);
        fieldSetFlags()[7] = true;
      }
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
    public io.skodjob.datagenerator.models.paymentfiat.Payer.Builder setName(java.lang.CharSequence value) {
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
    public io.skodjob.datagenerator.models.paymentfiat.Payer.Builder clearName() {
      name = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'payerType' field.
      * @return The value.
      */
    public java.lang.CharSequence getPayerType() {
      return payerType;
    }


    /**
      * Sets the value of the 'payerType' field.
      * @param value The value of 'payerType'.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.paymentfiat.Payer.Builder setPayerType(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.payerType = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'payerType' field has been set.
      * @return True if the 'payerType' field has been set, false otherwise.
      */
    public boolean hasPayerType() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'payerType' field.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.paymentfiat.Payer.Builder clearPayerType() {
      payerType = null;
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
    public io.skodjob.datagenerator.models.paymentfiat.Payer.Builder setAccountNumber(java.lang.CharSequence value) {
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
    public io.skodjob.datagenerator.models.paymentfiat.Payer.Builder clearAccountNumber() {
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
    public io.skodjob.datagenerator.models.paymentfiat.Payer.Builder setBank(java.lang.CharSequence value) {
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
    public io.skodjob.datagenerator.models.paymentfiat.Payer.Builder clearBank() {
      bank = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'billingAddress' field.
      * @return The value.
      */
    public io.skodjob.datagenerator.models.paymentfiat.Address getBillingAddress() {
      return billingAddress;
    }


    /**
      * Sets the value of the 'billingAddress' field.
      * @param value The value of 'billingAddress'.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.paymentfiat.Payer.Builder setBillingAddress(io.skodjob.datagenerator.models.paymentfiat.Address value) {
      validate(fields()[4], value);
      this.billingAddressBuilder = null;
      this.billingAddress = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'billingAddress' field has been set.
      * @return True if the 'billingAddress' field has been set, false otherwise.
      */
    public boolean hasBillingAddress() {
      return fieldSetFlags()[4];
    }

    /**
     * Gets the Builder instance for the 'billingAddress' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public io.skodjob.datagenerator.models.paymentfiat.Address.Builder getBillingAddressBuilder() {
      if (billingAddressBuilder == null) {
        if (hasBillingAddress()) {
          setBillingAddressBuilder(io.skodjob.datagenerator.models.paymentfiat.Address.newBuilder(billingAddress));
        } else {
          setBillingAddressBuilder(io.skodjob.datagenerator.models.paymentfiat.Address.newBuilder());
        }
      }
      return billingAddressBuilder;
    }

    /**
     * Sets the Builder instance for the 'billingAddress' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */

    public io.skodjob.datagenerator.models.paymentfiat.Payer.Builder setBillingAddressBuilder(io.skodjob.datagenerator.models.paymentfiat.Address.Builder value) {
      clearBillingAddress();
      billingAddressBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'billingAddress' field has an active Builder instance
     * @return True if the 'billingAddress' field has an active Builder instance
     */
    public boolean hasBillingAddressBuilder() {
      return billingAddressBuilder != null;
    }

    /**
      * Clears the value of the 'billingAddress' field.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.paymentfiat.Payer.Builder clearBillingAddress() {
      billingAddress = null;
      billingAddressBuilder = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'cardNumber' field.
      * @return The value.
      */
    public java.lang.CharSequence getCardNumber() {
      return cardNumber;
    }


    /**
      * Sets the value of the 'cardNumber' field.
      * @param value The value of 'cardNumber'.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.paymentfiat.Payer.Builder setCardNumber(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.cardNumber = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'cardNumber' field has been set.
      * @return True if the 'cardNumber' field has been set, false otherwise.
      */
    public boolean hasCardNumber() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'cardNumber' field.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.paymentfiat.Payer.Builder clearCardNumber() {
      cardNumber = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'cardType' field.
      * @return The value.
      */
    public java.lang.CharSequence getCardType() {
      return cardType;
    }


    /**
      * Sets the value of the 'cardType' field.
      * @param value The value of 'cardType'.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.paymentfiat.Payer.Builder setCardType(java.lang.CharSequence value) {
      validate(fields()[6], value);
      this.cardType = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'cardType' field has been set.
      * @return True if the 'cardType' field has been set, false otherwise.
      */
    public boolean hasCardType() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'cardType' field.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.paymentfiat.Payer.Builder clearCardType() {
      cardType = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'expiryDate' field.
      * @return The value.
      */
    public java.lang.CharSequence getExpiryDate() {
      return expiryDate;
    }


    /**
      * Sets the value of the 'expiryDate' field.
      * @param value The value of 'expiryDate'.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.paymentfiat.Payer.Builder setExpiryDate(java.lang.CharSequence value) {
      validate(fields()[7], value);
      this.expiryDate = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'expiryDate' field has been set.
      * @return True if the 'expiryDate' field has been set, false otherwise.
      */
    public boolean hasExpiryDate() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'expiryDate' field.
      * @return This builder.
      */
    public io.skodjob.datagenerator.models.paymentfiat.Payer.Builder clearExpiryDate() {
      expiryDate = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Payer build() {
      try {
        Payer record = new Payer();
        record.name = fieldSetFlags()[0] ? this.name : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.payerType = fieldSetFlags()[1] ? this.payerType : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.accountNumber = fieldSetFlags()[2] ? this.accountNumber : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.bank = fieldSetFlags()[3] ? this.bank : (java.lang.CharSequence) defaultValue(fields()[3]);
        if (billingAddressBuilder != null) {
          try {
            record.billingAddress = this.billingAddressBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("billingAddress"));
            throw e;
          }
        } else {
          record.billingAddress = fieldSetFlags()[4] ? this.billingAddress : (io.skodjob.datagenerator.models.paymentfiat.Address) defaultValue(fields()[4]);
        }
        record.cardNumber = fieldSetFlags()[5] ? this.cardNumber : (java.lang.CharSequence) defaultValue(fields()[5]);
        record.cardType = fieldSetFlags()[6] ? this.cardType : (java.lang.CharSequence) defaultValue(fields()[6]);
        record.expiryDate = fieldSetFlags()[7] ? this.expiryDate : (java.lang.CharSequence) defaultValue(fields()[7]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Payer>
    WRITER$ = (org.apache.avro.io.DatumWriter<Payer>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Payer>
    READER$ = (org.apache.avro.io.DatumReader<Payer>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.name);

    out.writeString(this.payerType);

    out.writeString(this.accountNumber);

    out.writeString(this.bank);

    this.billingAddress.customEncode(out);

    if (this.cardNumber == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.cardNumber);
    }

    if (this.cardType == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.cardType);
    }

    if (this.expiryDate == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.expiryDate);
    }

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.name = in.readString(this.name instanceof Utf8 ? (Utf8)this.name : null);

      this.payerType = in.readString(this.payerType instanceof Utf8 ? (Utf8)this.payerType : null);

      this.accountNumber = in.readString(this.accountNumber instanceof Utf8 ? (Utf8)this.accountNumber : null);

      this.bank = in.readString(this.bank instanceof Utf8 ? (Utf8)this.bank : null);

      if (this.billingAddress == null) {
        this.billingAddress = new io.skodjob.datagenerator.models.paymentfiat.Address();
      }
      this.billingAddress.customDecode(in);

      if (in.readIndex() != 1) {
        in.readNull();
        this.cardNumber = null;
      } else {
        this.cardNumber = in.readString(this.cardNumber instanceof Utf8 ? (Utf8)this.cardNumber : null);
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.cardType = null;
      } else {
        this.cardType = in.readString(this.cardType instanceof Utf8 ? (Utf8)this.cardType : null);
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.expiryDate = null;
      } else {
        this.expiryDate = in.readString(this.expiryDate instanceof Utf8 ? (Utf8)this.expiryDate : null);
      }

    } else {
      for (int i = 0; i < 8; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.name = in.readString(this.name instanceof Utf8 ? (Utf8)this.name : null);
          break;

        case 1:
          this.payerType = in.readString(this.payerType instanceof Utf8 ? (Utf8)this.payerType : null);
          break;

        case 2:
          this.accountNumber = in.readString(this.accountNumber instanceof Utf8 ? (Utf8)this.accountNumber : null);
          break;

        case 3:
          this.bank = in.readString(this.bank instanceof Utf8 ? (Utf8)this.bank : null);
          break;

        case 4:
          if (this.billingAddress == null) {
            this.billingAddress = new io.skodjob.datagenerator.models.paymentfiat.Address();
          }
          this.billingAddress.customDecode(in);
          break;

        case 5:
          if (in.readIndex() != 1) {
            in.readNull();
            this.cardNumber = null;
          } else {
            this.cardNumber = in.readString(this.cardNumber instanceof Utf8 ? (Utf8)this.cardNumber : null);
          }
          break;

        case 6:
          if (in.readIndex() != 1) {
            in.readNull();
            this.cardType = null;
          } else {
            this.cardType = in.readString(this.cardType instanceof Utf8 ? (Utf8)this.cardType : null);
          }
          break;

        case 7:
          if (in.readIndex() != 1) {
            in.readNull();
            this.expiryDate = null;
          } else {
            this.expiryDate = in.readString(this.expiryDate instanceof Utf8 ? (Utf8)this.expiryDate : null);
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










