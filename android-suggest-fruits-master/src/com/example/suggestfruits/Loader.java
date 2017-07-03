package com.example.suggestfruits;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import com.example.suggestfruits.exceptions.LoadFruitsFileException;

import android.content.Context;

public final class Loader {
	Context context;

	public Loader(Context context) {
		this.context = context;
	}

	public final CalendarFruit load() throws NullPointerException,
			LoadFruitsFileException {

		XmlPullParserFactory pullParserFactory;
		try {
			pullParserFactory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = pullParserFactory.newPullParser();

			InputStream in_s = context.getAssets().open(
					"fruits.xml");
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(in_s, null);

			return parseXML(parser);

		} catch (Exception e) {
			throw new LoadFruitsFileException(e);
		}


	}

	private CalendarFruit parseXML(XmlPullParser parser) throws XmlPullParserException,
			IOException {
		int eventType = parser.getEventType();

		CalendarFruit fruits = new CalendarFruit();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			String name = null;
			switch (eventType) {			
			case XmlPullParser.START_DOCUMENT:
				fruits = new CalendarFruit();
				break;
			case XmlPullParser.START_TAG:
				name = parser.getName();
				if (name.equals("enero") || name.equals("febrero") || name.equals("marzo") || name.equals("abril")
						|| name.equals("mayo") || name.equals("junio") || name.equals("julio") || name.equals("agosto")
						|| name.equals("setiembre") || name.equals("octubre") || name.equals("noviembre") || name.equals("diciembre")
						) {
					String values = parser.nextText();
					String [] vals = values.split(":");
					fruits.setFruits(name,vals);
				} 
			}
			eventType = parser.next();
		}
		
		return fruits;
	}

}
