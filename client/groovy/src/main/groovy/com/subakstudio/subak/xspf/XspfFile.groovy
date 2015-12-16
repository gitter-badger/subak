package com.subakstudio.subak.xspf

import groovy.xml.MarkupBuilder

/**
 * Created by yeoupooh on 12/15/15.
 */
class XspfFile {
    static void saveTo(model, file) {
        println('save to .xspf')

        def writer = new StringWriter()
        def xml = new MarkupBuilder(writer)
        xml.mkp.xmlDeclaration(version: "1.0", encoding: "utf-8")
        xml.playlist(xmlns: 'http://xspf.org/ns/0/', version: '1') {
            creator 'Automatically generated by subak groovy script'
            trackList {
                model.each { item ->
                    println(item)
                    track {
                        title item.track
                        creator item.artist
                        location String.valueOf(item.file)
                        if (item.length > 0) {
                            duration item.length
                        }
                    }
                }
            }
        }

        println(writer.toString())

        file.text = writer.toString()
    }
}
