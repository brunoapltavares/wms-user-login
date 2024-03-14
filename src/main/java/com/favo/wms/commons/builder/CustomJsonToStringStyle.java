package com.favo.wms.commons.builder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.text.StringEscapeUtils;

public class CustomJsonToStringStyle extends ToStringStyle {
	private static final long serialVersionUID = 4378260940947957089L;

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

	private static final String FIELD_NAME_QUOTE = "\"";

    public static final ToStringStyle CUSTOM_JSON_STYLE = new CustomJsonToStringStyle();

	/**
	 * <p>
	 * Constructor.
	 * </p>
	 *
	 * <p>
	 * Use the static constant rather than instantiating.
	 * </p>
	 */
	CustomJsonToStringStyle() {
		super();

		this.setUseClassName(false);
		this.setUseIdentityHashCode(false);

		this.setContentStart("{");
		this.setContentEnd("}");

		this.setArrayStart("[");
		this.setArrayEnd("]");

		this.setFieldSeparator(",");
		this.setFieldNameValueSeparator(":");

		this.setNullText("null");

		this.setSummaryObjectStartText("\"<");
		this.setSummaryObjectEndText(">\"");

		this.setSizeStartText("\"<size=");
		this.setSizeEndText(">\"");
	}

	@Override
	public void append(final StringBuffer buffer, final String fieldName, final Object[] array,
			final Boolean fullDetail) {

		if (fieldName == null) {
			throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
		}
		if (!isFullDetail(fullDetail)) {
			throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
		}

		super.append(buffer, fieldName, array, fullDetail);
	}

	@Override
	public void append(final StringBuffer buffer, final String fieldName, final long[] array,
			final Boolean fullDetail) {

		if (fieldName == null) {
			throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
		}
		if (!isFullDetail(fullDetail)) {
			throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
		}

		super.append(buffer, fieldName, array, fullDetail);
	}

	@Override
	public void append(final StringBuffer buffer, final String fieldName, final int[] array, final Boolean fullDetail) {

		if (fieldName == null) {
			throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
		}
		if (!isFullDetail(fullDetail)) {
			throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
		}

		super.append(buffer, fieldName, array, fullDetail);
	}

	@Override
	public void append(final StringBuffer buffer, final String fieldName, final short[] array,
			final Boolean fullDetail) {

		if (fieldName == null) {
			throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
		}
		if (!isFullDetail(fullDetail)) {
			throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
		}

		super.append(buffer, fieldName, array, fullDetail);
	}

	@Override
	public void append(final StringBuffer buffer, final String fieldName, final byte[] array,
			final Boolean fullDetail) {

		if (fieldName == null) {
			throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
		}
		if (!isFullDetail(fullDetail)) {
			throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
		}

		super.append(buffer, fieldName, array, fullDetail);
	}

	@Override
	public void append(final StringBuffer buffer, final String fieldName, final char[] array,
			final Boolean fullDetail) {

		if (fieldName == null) {
			throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
		}
		if (!isFullDetail(fullDetail)) {
			throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
		}

		super.append(buffer, fieldName, array, fullDetail);
	}

	@Override
	public void append(final StringBuffer buffer, final String fieldName, final double[] array,
			final Boolean fullDetail) {

		if (fieldName == null) {
			throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
		}
		if (!isFullDetail(fullDetail)) {
			throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
		}

		super.append(buffer, fieldName, array, fullDetail);
	}

	@Override
	public void append(final StringBuffer buffer, final String fieldName, final float[] array,
			final Boolean fullDetail) {

		if (fieldName == null) {
			throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
		}
		if (!isFullDetail(fullDetail)) {
			throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
		}

		super.append(buffer, fieldName, array, fullDetail);
	}

	@Override
	public void append(final StringBuffer buffer, final String fieldName, final boolean[] array,
			final Boolean fullDetail) {

		if (fieldName == null) {
			throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
		}
		if (!isFullDetail(fullDetail)) {
			throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
		}

		super.append(buffer, fieldName, array, fullDetail);
	}

	@Override
	public void append(final StringBuffer buffer, final String fieldName, final Object value,
			final Boolean fullDetail) {

		if (fieldName == null) {
			throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
		}
		if (!isFullDetail(fullDetail)) {
			throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
		}

		super.append(buffer, fieldName, value, fullDetail);
	}

	@Override
	protected void appendDetail(final StringBuffer buffer, final String fieldName, final char value) {
		appendValueAsString(buffer, String.valueOf(value));
	}

	@Override
	protected void appendDetail(final StringBuffer buffer, final String fieldName, final Object value) {

		if (value == null) {
			appendNullText(buffer, fieldName);
			return;
		}

		if (value instanceof Date) {
			String formatedDate = sdf.format(value);
			appendValueAsString(buffer, formatedDate);
			return;
		}

		if (value instanceof String || value instanceof Character) {
			appendValueAsString(buffer, value.toString());
			return;
		}

		if (value instanceof Number || value instanceof Boolean) {
			buffer.append(value);
			return;
		}

		final String valueAsString = value.toString();
		if (isJsonObject(valueAsString) || isJsonArray(valueAsString)) {
			buffer.append(value);
			return;
		}

		appendDetail(buffer, fieldName, valueAsString);
	}

	@Override
	protected void appendDetail(final StringBuffer buffer, final String fieldName, final Map<?, ?> map) {
		if (map != null && !map.isEmpty()) {
			buffer.append(getContentStart());

			boolean firstItem = true;
			for (final Entry<?, ?> entry : map.entrySet()) {
				final String keyStr = Objects.toString(entry.getKey(), null);
				if (keyStr != null) {
					if (firstItem) {
						firstItem = false;
					} else {
						appendFieldEnd(buffer, keyStr);
					}
					appendFieldStart(buffer, keyStr);
					final Object value = entry.getValue();
					if (value == null) {
						appendNullText(buffer, keyStr);
					} else {
						appendInternal(buffer, keyStr, value, true);
					}
				}
			}

			buffer.append(getContentEnd());
			return;
		}

		buffer.append(map);
	}

	private boolean isJsonArray(final String valueAsString) {
		return valueAsString.startsWith(getArrayStart()) && valueAsString.endsWith(getArrayEnd());
	}

	private boolean isJsonObject(final String valueAsString) {
		return valueAsString.startsWith(getContentStart()) && valueAsString.endsWith(getContentEnd());
	}

	/**
	 * Appends the given String enclosed in double-quotes to the given StringBuffer.
	 *
	 * @param buffer the StringBuffer to append the value to.
	 * @param value  the value to append.
	 */
	private void appendValueAsString(final StringBuffer buffer, final String value) {
		buffer.append('"').append(StringEscapeUtils.escapeJson(value)).append('"');
	}

	@Override
	protected void appendFieldStart(final StringBuffer buffer, final String fieldName) {

		if (fieldName == null) {
			throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
		}

		super.appendFieldStart(buffer, FIELD_NAME_QUOTE + StringEscapeUtils.escapeJson(fieldName) + FIELD_NAME_QUOTE);
	}

	/**
	 * <p>
	 * Ensure {@code Singleton} after serialization.
	 * </p>
	 *
	 * @return the singleton
	 */
	private Object readResolve() {
		return JSON_STYLE;
	}
}
