/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package data.avro;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class DataPiece extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -4654015977757295534L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"DataPiece\",\"namespace\":\"data.avro\",\"fields\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"intval\",\"type\":[\"int\",\"null\"]},{\"name\":\"textval\",\"type\":[\"string\",\"null\"]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<DataPiece> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<DataPiece> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<DataPiece> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<DataPiece> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<DataPiece> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this DataPiece to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a DataPiece from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a DataPiece instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static DataPiece fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.CharSequence name;
  private java.lang.Integer intval;
  private java.lang.CharSequence textval;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public DataPiece() {}

  /**
   * All-args constructor.
   * @param name The new value for name
   * @param intval The new value for intval
   * @param textval The new value for textval
   */
  public DataPiece(java.lang.CharSequence name, java.lang.Integer intval, java.lang.CharSequence textval) {
    this.name = name;
    this.intval = intval;
    this.textval = textval;
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
    case 1: return intval;
    case 2: return textval;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: name = (java.lang.CharSequence)value$; break;
    case 1: intval = (java.lang.Integer)value$; break;
    case 2: textval = (java.lang.CharSequence)value$; break;
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
   * Gets the value of the 'intval' field.
   * @return The value of the 'intval' field.
   */
  public java.lang.Integer getIntval() {
    return intval;
  }


  /**
   * Sets the value of the 'intval' field.
   * @param value the value to set.
   */
  public void setIntval(java.lang.Integer value) {
    this.intval = value;
  }

  /**
   * Gets the value of the 'textval' field.
   * @return The value of the 'textval' field.
   */
  public java.lang.CharSequence getTextval() {
    return textval;
  }


  /**
   * Sets the value of the 'textval' field.
   * @param value the value to set.
   */
  public void setTextval(java.lang.CharSequence value) {
    this.textval = value;
  }

  /**
   * Creates a new DataPiece RecordBuilder.
   * @return A new DataPiece RecordBuilder
   */
  public static data.avro.DataPiece.Builder newBuilder() {
    return new data.avro.DataPiece.Builder();
  }

  /**
   * Creates a new DataPiece RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new DataPiece RecordBuilder
   */
  public static data.avro.DataPiece.Builder newBuilder(data.avro.DataPiece.Builder other) {
    if (other == null) {
      return new data.avro.DataPiece.Builder();
    } else {
      return new data.avro.DataPiece.Builder(other);
    }
  }

  /**
   * Creates a new DataPiece RecordBuilder by copying an existing DataPiece instance.
   * @param other The existing instance to copy.
   * @return A new DataPiece RecordBuilder
   */
  public static data.avro.DataPiece.Builder newBuilder(data.avro.DataPiece other) {
    if (other == null) {
      return new data.avro.DataPiece.Builder();
    } else {
      return new data.avro.DataPiece.Builder(other);
    }
  }

  /**
   * RecordBuilder for DataPiece instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<DataPiece>
    implements org.apache.avro.data.RecordBuilder<DataPiece> {

    private java.lang.CharSequence name;
    private java.lang.Integer intval;
    private java.lang.CharSequence textval;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(data.avro.DataPiece.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.intval)) {
        this.intval = data().deepCopy(fields()[1].schema(), other.intval);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.textval)) {
        this.textval = data().deepCopy(fields()[2].schema(), other.textval);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing DataPiece instance
     * @param other The existing instance to copy.
     */
    private Builder(data.avro.DataPiece other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.intval)) {
        this.intval = data().deepCopy(fields()[1].schema(), other.intval);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.textval)) {
        this.textval = data().deepCopy(fields()[2].schema(), other.textval);
        fieldSetFlags()[2] = true;
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
    public data.avro.DataPiece.Builder setName(java.lang.CharSequence value) {
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
    public data.avro.DataPiece.Builder clearName() {
      name = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'intval' field.
      * @return The value.
      */
    public java.lang.Integer getIntval() {
      return intval;
    }


    /**
      * Sets the value of the 'intval' field.
      * @param value The value of 'intval'.
      * @return This builder.
      */
    public data.avro.DataPiece.Builder setIntval(java.lang.Integer value) {
      validate(fields()[1], value);
      this.intval = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'intval' field has been set.
      * @return True if the 'intval' field has been set, false otherwise.
      */
    public boolean hasIntval() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'intval' field.
      * @return This builder.
      */
    public data.avro.DataPiece.Builder clearIntval() {
      intval = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'textval' field.
      * @return The value.
      */
    public java.lang.CharSequence getTextval() {
      return textval;
    }


    /**
      * Sets the value of the 'textval' field.
      * @param value The value of 'textval'.
      * @return This builder.
      */
    public data.avro.DataPiece.Builder setTextval(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.textval = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'textval' field has been set.
      * @return True if the 'textval' field has been set, false otherwise.
      */
    public boolean hasTextval() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'textval' field.
      * @return This builder.
      */
    public data.avro.DataPiece.Builder clearTextval() {
      textval = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public DataPiece build() {
      try {
        DataPiece record = new DataPiece();
        record.name = fieldSetFlags()[0] ? this.name : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.intval = fieldSetFlags()[1] ? this.intval : (java.lang.Integer) defaultValue(fields()[1]);
        record.textval = fieldSetFlags()[2] ? this.textval : (java.lang.CharSequence) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<DataPiece>
    WRITER$ = (org.apache.avro.io.DatumWriter<DataPiece>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<DataPiece>
    READER$ = (org.apache.avro.io.DatumReader<DataPiece>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.name);

    if (this.intval == null) {
      out.writeIndex(1);
      out.writeNull();
    } else {
      out.writeIndex(0);
      out.writeInt(this.intval);
    }

    if (this.textval == null) {
      out.writeIndex(1);
      out.writeNull();
    } else {
      out.writeIndex(0);
      out.writeString(this.textval);
    }

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.name = in.readString(this.name instanceof Utf8 ? (Utf8)this.name : null);

      if (in.readIndex() != 0) {
        in.readNull();
        this.intval = null;
      } else {
        this.intval = in.readInt();
      }

      if (in.readIndex() != 0) {
        in.readNull();
        this.textval = null;
      } else {
        this.textval = in.readString(this.textval instanceof Utf8 ? (Utf8)this.textval : null);
      }

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.name = in.readString(this.name instanceof Utf8 ? (Utf8)this.name : null);
          break;

        case 1:
          if (in.readIndex() != 0) {
            in.readNull();
            this.intval = null;
          } else {
            this.intval = in.readInt();
          }
          break;

        case 2:
          if (in.readIndex() != 0) {
            in.readNull();
            this.textval = null;
          } else {
            this.textval = in.readString(this.textval instanceof Utf8 ? (Utf8)this.textval : null);
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










