<?xml version="1.0" encoding="UTF-8"?>
<jasperReport xmlns="http://jasperreports.sourceforge.net/jasperreports" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd" name="rptDaot" language="groovy" pageWidth="1080" pageHeight="1190" columnWidth="1040" leftMargin="20" rightMargin="20" topMargin="20" bottomMargin="20">
	<property name="ireport.zoom" value="1.4641000000000006"/>
	<property name="ireport.x" value="278"/>
	<property name="ireport.y" value="0"/>
	<field name="CONTADOR" class="java.lang.String"/>
	<field name="D_TIPODOC" class="java.lang.String"/>
	<field name="D_NUMDOC" class="java.lang.String"/>
	<field name="PERIODO" class="java.lang.String"/>
	<field name="TIPO_PER" class="java.lang.String"/>
	<field name="TIPO_DOC" class="java.lang.String"/>
	<field name="IMPORTE" class="java.math.BigDecimal"/>
	<field name="AP_PATERNO" class="java.lang.String"/>
	<field name="AP_MATERNO" class="java.lang.String"/>
	<field name="NOMBRE1" class="java.lang.String"/>
	<field name="NOMBRE2" class="java.lang.String"/>
	<field name="RAZON_SOCIAL" class="java.lang.String"/>
	<field name="FORMULA" class="java.lang.String"/>
	<field name="NUMDOC" class="java.lang.String"/>
	<background>
		<band splitType="Stretch"/>
	</background>
	<title>
		<band height="20">
			<staticText>
				<reportElement x="0" y="0" width="40" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<text><![CDATA[CONTADOR]]></text>
			</staticText>
			<staticText>
				<reportElement x="40" y="0" width="40" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<text><![CDATA[D_TIPODOC]]></text>
			</staticText>
			<staticText>
				<reportElement x="80" y="0" width="49" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<text><![CDATA[D_NUMDOC]]></text>
			</staticText>
			<staticText>
				<reportElement x="129" y="0" width="38" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<text><![CDATA[PERIODO]]></text>
			</staticText>
			<staticText>
				<reportElement x="167" y="0" width="36" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<text><![CDATA[TIPO_PER]]></text>
			</staticText>
			<staticText>
				<reportElement x="203" y="0" width="35" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<text><![CDATA[TIPO_DOC]]></text>
			</staticText>
			<staticText>
				<reportElement x="318" y="0" width="47" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<text><![CDATA[IMPORTE]]></text>
			</staticText>
			<staticText>
				<reportElement x="365" y="0" width="62" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<text><![CDATA[AP_PATERNO]]></text>
			</staticText>
			<staticText>
				<reportElement x="427" y="0" width="62" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<text><![CDATA[AP_MATERNO]]></text>
			</staticText>
			<staticText>
				<reportElement x="489" y="0" width="62" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<text><![CDATA[NOMBRE 1]]></text>
			</staticText>
			<staticText>
				<reportElement x="551" y="0" width="60" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<text><![CDATA[NOMBRE 2]]></text>
			</staticText>
			<staticText>
				<reportElement x="611" y="0" width="182" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<text><![CDATA[RAZON SOCIAL]]></text>
			</staticText>
			<staticText>
				<reportElement x="793" y="0" width="247" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<text><![CDATA[FORMULA]]></text>
			</staticText>
			<staticText>
				<reportElement x="238" y="0" width="80" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<text><![CDATA[NUM_DOC]]></text>
			</staticText>
		</band>
	</title>
	<detail>
		<band height="20" splitType="Stretch">
			<textField isBlankWhenNull="true">
				<reportElement x="0" y="0" width="40" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<textFieldExpression class="java.lang.String"><![CDATA[$F{CONTADOR}]]></textFieldExpression>
			</textField>
			<textField isBlankWhenNull="true">
				<reportElement x="40" y="0" width="40" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<textFieldExpression class="java.lang.String"><![CDATA[$F{D_TIPODOC}]]></textFieldExpression>
			</textField>
			<textField isBlankWhenNull="true">
				<reportElement x="80" y="0" width="49" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<textFieldExpression class="java.lang.String"><![CDATA[$F{D_NUMDOC}]]></textFieldExpression>
			</textField>
			<textField isBlankWhenNull="true">
				<reportElement x="129" y="0" width="38" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<textFieldExpression class="java.lang.String"><![CDATA[$F{PERIODO}]]></textFieldExpression>
			</textField>
			<textField isBlankWhenNull="true">
				<reportElement x="167" y="0" width="36" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<textFieldExpression class="java.lang.String"><![CDATA[$F{TIPO_PER}]]></textFieldExpression>
			</textField>
			<textField isBlankWhenNull="true">
				<reportElement x="203" y="0" width="35" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<textFieldExpression class="java.lang.String"><![CDATA[$F{TIPO_DOC}]]></textFieldExpression>
			</textField>
			<textField pattern="#,##" isBlankWhenNull="true">
				<reportElement x="318" y="0" width="47" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<textFieldExpression class="java.lang.String"><![CDATA[$F{IMPORTE}]]></textFieldExpression>
			</textField>
			<textField isBlankWhenNull="true">
				<reportElement x="365" y="0" width="62" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<textFieldExpression class="java.lang.String"><![CDATA[$F{AP_PATERNO}]]></textFieldExpression>
			</textField>
			<textField isBlankWhenNull="true">
				<reportElement x="427" y="0" width="62" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<textFieldExpression class="java.lang.String"><![CDATA[$F{AP_MATERNO}]]></textFieldExpression>
			</textField>
			<textField isBlankWhenNull="true">
				<reportElement x="489" y="0" width="62" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<textFieldExpression class="java.lang.String"><![CDATA[$F{NOMBRE1}]]></textFieldExpression>
			</textField>
			<textField isBlankWhenNull="true">
				<reportElement x="551" y="0" width="60" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<textFieldExpression class="java.lang.String"><![CDATA[$F{NOMBRE2}]]></textFieldExpression>
			</textField>
			<textField isBlankWhenNull="true">
				<reportElement x="611" y="0" width="182" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<textFieldExpression class="java.lang.String"><![CDATA[$F{RAZON_SOCIAL}]]></textFieldExpression>
			</textField>
			<textField isBlankWhenNull="true">
				<reportElement x="793" y="0" width="247" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<textFieldExpression class="java.lang.String"><![CDATA[$F{FORMULA}]]></textFieldExpression>
			</textField>
			<textField isBlankWhenNull="true">
				<reportElement x="238" y="0" width="80" height="20"/>
				<box>
					<topPen lineWidth="0.5"/>
					<leftPen lineWidth="0.5"/>
					<bottomPen lineWidth="0.5"/>
					<rightPen lineWidth="0.5"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="6"/>
				</textElement>
				<textFieldExpression class="java.lang.String"><![CDATA[$F{NUMDOC}]]></textFieldExpression>
			</textField>
		</band>
	</detail>
</jasperReport>
