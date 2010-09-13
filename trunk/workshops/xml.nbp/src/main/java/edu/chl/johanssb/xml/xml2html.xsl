<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : xml2html.xsl
    Created on : September 6, 2010, 4:59 PM
    Author     : flipmo
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    version="1.0"
    xmlns:cl='http://edu.chl.johanssb/schema/customerList'
    xmlns:cu='http://edu.chl.johanssb/schema/customer'>

    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
                <title>xml2html.xsl</title>
            </head>
            <body>
                <table border="1">
                    <tr>
                        <td>id</td>
                        <td>fname</td>
                        <td>lname</td>
                        <td>age</td>
                        <td>address</td>
                        <td>email</td>
                    </tr>
                    <xsl:for-each select="cl:customerList/cl:customer">
                        <tr>
                            <td>
                                <xsl:value-of select="@id"/>
                            </td>
                            <td>
                                <xsl:value-of select="cu:fname"/>
                            </td>
                            <td>
                                <xsl:value-of select="cu:lname"/>
                            </td>
                            <td>
                                <xsl:value-of select="cu:age"/>
                            </td>
                            <td>
                                <xsl:value-of select="cu:address"/>
                            </td>
                            <td>
                                <xsl:value-of select="cu:email"/>
                            </td>
                        </tr>
                    </xsl:for-each>

                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
