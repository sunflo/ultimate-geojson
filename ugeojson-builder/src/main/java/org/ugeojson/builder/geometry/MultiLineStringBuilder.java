package org.ugeojson.builder.geometry;

import java.util.List;

import org.ugeojson.builder.common.BuilderConstants;
import org.ugeojson.model.GeoJSONObjectTypeEnum;
import org.ugeojson.model.geometry.LineStringDto;
import org.ugeojson.model.geometry.MultiLineStringDto;

/**
 * @author moksuzer
 *
 */
public class MultiLineStringBuilder extends GeometryBuilder<MultiLineStringDto> {

	private static final MultiLineStringBuilder INSTANCE = new MultiLineStringBuilder();

	public static MultiLineStringBuilder getInstance() {
		return INSTANCE;
	}

	private MultiLineStringBuilder() {
	}

	@Override
	public String toGeoJSON(MultiLineStringDto multiLineString) {
		if (multiLineString == null || multiLineString.getLines() == null || multiLineString.getLines().isEmpty()) {
			return BuilderConstants.NULL_VALUE;
		}

		StringBuilder builder = initializeBuilder();
		buildTypePart(builder, GeoJSONObjectTypeEnum.MultiLineString);

		builder.append(BuilderConstants.COORDINATES_SPACE);
		builder.append(BuilderConstants.OPEN_BRACKET);
		builder.append(BuilderConstants.NEWLINE);

		List<LineStringDto> lines = multiLineString.getLines();

		for (int i = 0; i < lines.size(); i++) {
			builder.append(BuilderConstants.OPEN_BRACKET);
			builder.append(BuilderConstants.NEWLINE);

			buildLineStringPositions(builder, lines.get(i));

			builder.append(BuilderConstants.CLOSE_BRACKET);
			if (i < lines.size() - 1) {
				builder.append(BuilderConstants.COMMA_NEWLINE);
			} else {
				builder.append(BuilderConstants.NEWLINE);
			}

		}

		builder.append(BuilderConstants.CLOSE_BRACKET);

		buildBbox(builder, multiLineString.getBbox());
		endBuilder(builder);

		return builder.toString();
	}

}
