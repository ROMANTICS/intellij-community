// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

// This is a generated file. Not intended for manual editing.
package org.jetbrains.plugins.groovy.lang.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.plugins.groovy.lang.lexer.GroovyElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrAnnotationElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrAnnotationArgumentListElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrNameValuePairElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrAnnotationMethodElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrAnnotationDefinitionElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrAnonymousElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrTypeDefinitionBodyElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrClassDefinitionElementType;
import org.jetbrains.plugins.groovy.lang.parser.GrClosureElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrConstructorElementType;
import org.jetbrains.plugins.groovy.lang.parser.GrConstructorBlockElementType;
import org.jetbrains.plugins.groovy.lang.parser.GrEnumDefinitionBodyElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrEnumConstantElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrEnumConstantListElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrEnumConstantInitializerElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrEnumDefinitionElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrExtendsClauseElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrFieldElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrImplementsClauseElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrImportStatementElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrInterfaceDefinitionElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrMethodElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrModifierListElementType;
import org.jetbrains.plugins.groovy.lang.parser.GrBlockElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrPackageDefinitionElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrParameterElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrParameterListElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrThrowsClauseElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrTraitElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrTypeParameterElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrTypeParameterBoundsElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrTypeParameterListElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrVariableElementType;
import org.jetbrains.plugins.groovy.lang.psi.stubs.elements.GrVariableDeclarationElementType;

public interface GroovyElementTypes {

  GroovyElementType ADDITIVE_EXPRESSION = new GroovyElementType("ADDITIVE_EXPRESSION");
  GrAnnotationElementType ANNOTATION = new GrAnnotationElementType("ANNOTATION");
  GrAnnotationArgumentListElementType ANNOTATION_ARGUMENT_LIST = new GrAnnotationArgumentListElementType("ANNOTATION_ARGUMENT_LIST");
  GroovyElementType ANNOTATION_ARRAY_VALUE = new GroovyElementType("ANNOTATION_ARRAY_VALUE");
  GrNameValuePairElementType ANNOTATION_MEMBER_VALUE_PAIR = new GrNameValuePairElementType("ANNOTATION_MEMBER_VALUE_PAIR");
  GrAnnotationMethodElementType ANNOTATION_METHOD = new GrAnnotationMethodElementType("ANNOTATION_METHOD");
  GrAnnotationDefinitionElementType ANNOTATION_TYPE_DEFINITION = new GrAnnotationDefinitionElementType("ANNOTATION_TYPE_DEFINITION");
  GrAnonymousElementType ANONYMOUS_TYPE_DEFINITION = new GrAnonymousElementType("ANONYMOUS_TYPE_DEFINITION");
  GroovyElementType APPLICATION_ARGUMENT_LIST = new GroovyElementType("APPLICATION_ARGUMENT_LIST");
  GroovyElementType APPLICATION_EXPRESSION = new GroovyElementType("APPLICATION_EXPRESSION");
  GroovyElementType APPLICATION_INDEX = new GroovyElementType("APPLICATION_INDEX");
  GroovyElementType ARGUMENT_LABEL = new GroovyElementType("ARGUMENT_LABEL");
  GroovyElementType ARGUMENT_LIST = new GroovyElementType("ARGUMENT_LIST");
  GroovyElementType ARRAY_DECLARATION = new GroovyElementType("ARRAY_DECLARATION");
  GroovyElementType ARRAY_TYPE_ELEMENT = new GroovyElementType("ARRAY_TYPE_ELEMENT");
  GroovyElementType ASSERT_STATEMENT = new GroovyElementType("ASSERT_STATEMENT");
  GroovyElementType ASSIGNMENT_EXPRESSION = new GroovyElementType("ASSIGNMENT_EXPRESSION");
  GroovyElementType AS_EXPRESSION = new GroovyElementType("AS_EXPRESSION");
  GroovyElementType BAND_EXPRESSION = new GroovyElementType("BAND_EXPRESSION");
  GroovyElementType BLOCK_STATEMENT = new GroovyElementType("BLOCK_STATEMENT");
  GroovyElementType BOR_EXPRESSION = new GroovyElementType("BOR_EXPRESSION");
  GroovyElementType BREAK_STATEMENT = new GroovyElementType("BREAK_STATEMENT");
  GroovyElementType BUILT_IN_TYPE_EXPRESSION = new GroovyElementType("BUILT_IN_TYPE_EXPRESSION");
  GroovyElementType CASE_LABEL = new GroovyElementType("CASE_LABEL");
  GroovyElementType CASE_SECTION = new GroovyElementType("CASE_SECTION");
  GroovyElementType CAST_EXPRESSION = new GroovyElementType("CAST_EXPRESSION");
  GroovyElementType CATCH_CLAUSE = new GroovyElementType("CATCH_CLAUSE");
  GrTypeDefinitionBodyElementType CLASS_BODY = new GrTypeDefinitionBodyElementType("CLASS_BODY");
  GroovyElementType CLASS_INITIALIZER = new GroovyElementType("CLASS_INITIALIZER");
  GrClassDefinitionElementType CLASS_TYPE_DEFINITION = new GrClassDefinitionElementType("CLASS_TYPE_DEFINITION");
  GroovyElementType CLASS_TYPE_ELEMENT = new GroovyElementType("CLASS_TYPE_ELEMENT");
  GrClosureElementType CLOSURE = new GrClosureElementType("CLOSURE");
  GroovyElementType CODE_REFERENCE = new GroovyElementType("CODE_REFERENCE");
  GrConstructorElementType CONSTRUCTOR = new GrConstructorElementType("CONSTRUCTOR");
  GrConstructorBlockElementType CONSTRUCTOR_BLOCK = new GrConstructorBlockElementType("CONSTRUCTOR_BLOCK");
  GroovyElementType CONSTRUCTOR_CALL_EXPRESSION = new GroovyElementType("CONSTRUCTOR_CALL_EXPRESSION");
  GroovyElementType CONTINUE_STATEMENT = new GroovyElementType("CONTINUE_STATEMENT");
  GroovyElementType DISJUNCTION_TYPE_ELEMENT = new GroovyElementType("DISJUNCTION_TYPE_ELEMENT");
  GroovyElementType DOLLAR_SLASHY_LITERAL = new GroovyElementType("DOLLAR_SLASHY_LITERAL");
  GroovyElementType ELVIS_EXPRESSION = new GroovyElementType("ELVIS_EXPRESSION");
  GrEnumDefinitionBodyElementType ENUM_BODY = new GrEnumDefinitionBodyElementType("ENUM_BODY");
  GrEnumConstantElementType ENUM_CONSTANT = new GrEnumConstantElementType("ENUM_CONSTANT");
  GrEnumConstantListElementType ENUM_CONSTANTS = new GrEnumConstantListElementType("ENUM_CONSTANTS");
  GrEnumConstantInitializerElementType ENUM_CONSTANT_INITIALIZER = new GrEnumConstantInitializerElementType("ENUM_CONSTANT_INITIALIZER");
  GrEnumDefinitionElementType ENUM_TYPE_DEFINITION = new GrEnumDefinitionElementType("ENUM_TYPE_DEFINITION");
  GroovyElementType EQUALITY_EXPRESSION = new GroovyElementType("EQUALITY_EXPRESSION");
  GroovyElementType EXPRESSION = new GroovyElementType("EXPRESSION");
  GrExtendsClauseElementType EXTENDS_CLAUSE = new GrExtendsClauseElementType("EXTENDS_CLAUSE");
  GrFieldElementType FIELD = new GrFieldElementType("FIELD");
  GroovyElementType FINALLY_CLAUSE = new GroovyElementType("FINALLY_CLAUSE");
  GroovyElementType FOR_IN_CLAUSE = new GroovyElementType("FOR_IN_CLAUSE");
  GroovyElementType FOR_STATEMENT = new GroovyElementType("FOR_STATEMENT");
  GroovyElementType GSTRING = new GroovyElementType("GSTRING");
  GroovyElementType IF_STATEMENT = new GroovyElementType("IF_STATEMENT");
  GrImplementsClauseElementType IMPLEMENTS_CLAUSE = new GrImplementsClauseElementType("IMPLEMENTS_CLAUSE");
  GrImportStatementElementType IMPORT = new GrImportStatementElementType("IMPORT");
  GroovyElementType IMPORT_ALIAS = new GroovyElementType("IMPORT_ALIAS");
  GroovyElementType INDEX_EXPRESSION = new GroovyElementType("INDEX_EXPRESSION");
  GroovyElementType INSTANCEOF_EXPRESSION = new GroovyElementType("INSTANCEOF_EXPRESSION");
  GrInterfaceDefinitionElementType INTERFACE_TYPE_DEFINITION = new GrInterfaceDefinitionElementType("INTERFACE_TYPE_DEFINITION");
  GroovyElementType LABELED_STATEMENT = new GroovyElementType("LABELED_STATEMENT");
  GroovyElementType LAND_EXPRESSION = new GroovyElementType("LAND_EXPRESSION");
  GroovyElementType LEFT_SHIFT_SIGN = new GroovyElementType("LEFT_SHIFT_SIGN");
  GroovyElementType LIST_OR_MAP = new GroovyElementType("LIST_OR_MAP");
  GroovyElementType LITERAL = new GroovyElementType("LITERAL");
  GroovyElementType LOR_EXPRESSION = new GroovyElementType("LOR_EXPRESSION");
  GrMethodElementType METHOD = new GrMethodElementType("METHOD");
  GroovyElementType METHOD_CALL_EXPRESSION = new GroovyElementType("METHOD_CALL_EXPRESSION");
  GrModifierListElementType MODIFIER_LIST = new GrModifierListElementType("MODIFIER_LIST");
  GroovyElementType MULTIPLICATIVE_EXPRESSION = new GroovyElementType("MULTIPLICATIVE_EXPRESSION");
  GroovyElementType NAMED_ARGUMENT = new GroovyElementType("NAMED_ARGUMENT");
  GroovyElementType NEW_EXPRESSION = new GroovyElementType("NEW_EXPRESSION");
  GrBlockElementType OPEN_BLOCK = new GrBlockElementType("OPEN_BLOCK");
  GrPackageDefinitionElementType PACKAGE_DEFINITION = new GrPackageDefinitionElementType("PACKAGE_DEFINITION");
  GrParameterElementType PARAMETER = new GrParameterElementType("PARAMETER");
  GrParameterListElementType PARAMETER_LIST = new GrParameterListElementType("PARAMETER_LIST");
  GroovyElementType PARENTHESIZED_EXPRESSION = new GroovyElementType("PARENTHESIZED_EXPRESSION");
  GroovyElementType POWER_EXPRESSION = new GroovyElementType("POWER_EXPRESSION");
  GroovyElementType PRIMITIVE_TYPE_ELEMENT = new GroovyElementType("PRIMITIVE_TYPE_ELEMENT");
  GroovyElementType PROPERTY_EXPRESSION = new GroovyElementType("PROPERTY_EXPRESSION");
  GroovyElementType RANGE_EXPRESSION = new GroovyElementType("RANGE_EXPRESSION");
  GroovyElementType REFERENCE_EXPRESSION = new GroovyElementType("REFERENCE_EXPRESSION");
  GroovyElementType REGEX = new GroovyElementType("REGEX");
  GroovyElementType REGEX_FIND_EXPRESSION = new GroovyElementType("REGEX_FIND_EXPRESSION");
  GroovyElementType REGEX_MATCH_EXPRESSION = new GroovyElementType("REGEX_MATCH_EXPRESSION");
  GroovyElementType RELATIONAL_EXPRESSION = new GroovyElementType("RELATIONAL_EXPRESSION");
  GroovyElementType RETURN_STATEMENT = new GroovyElementType("RETURN_STATEMENT");
  GroovyElementType RIGHT_SHIFT_SIGN = new GroovyElementType("RIGHT_SHIFT_SIGN");
  GroovyElementType RIGHT_SHIFT_UNSIGNED_SIGN = new GroovyElementType("RIGHT_SHIFT_UNSIGNED_SIGN");
  GroovyElementType SHIFT_EXPRESSION = new GroovyElementType("SHIFT_EXPRESSION");
  GroovyElementType SLASHY_LITERAL = new GroovyElementType("SLASHY_LITERAL");
  GroovyElementType SPREAD_LIST_ARGUMENT = new GroovyElementType("SPREAD_LIST_ARGUMENT");
  GroovyElementType STRING_CONTENT = new GroovyElementType("STRING_CONTENT");
  GroovyElementType STRING_INJECTION = new GroovyElementType("STRING_INJECTION");
  GroovyElementType SWITCH_STATEMENT = new GroovyElementType("SWITCH_STATEMENT");
  GroovyElementType SYNCHRONIZED_STATEMENT = new GroovyElementType("SYNCHRONIZED_STATEMENT");
  GroovyElementType TERNARY_EXPRESSION = new GroovyElementType("TERNARY_EXPRESSION");
  GrThrowsClauseElementType THROWS_CLAUSE = new GrThrowsClauseElementType("THROWS_CLAUSE");
  GroovyElementType THROW_STATEMENT = new GroovyElementType("THROW_STATEMENT");
  GroovyElementType TRADITIONAL_FOR_CLAUSE = new GroovyElementType("TRADITIONAL_FOR_CLAUSE");
  GrTraitElementType TRAIT_TYPE_DEFINITION = new GrTraitElementType("TRAIT_TYPE_DEFINITION");
  GroovyElementType TRY_STATEMENT = new GroovyElementType("TRY_STATEMENT");
  GroovyElementType TUPLE = new GroovyElementType("TUPLE");
  GroovyElementType TUPLE_ASSIGNMENT_EXPRESSION = new GroovyElementType("TUPLE_ASSIGNMENT_EXPRESSION");
  GroovyElementType TYPE_ARGUMENT_LIST = new GroovyElementType("TYPE_ARGUMENT_LIST");
  GroovyElementType TYPE_ELEMENT = new GroovyElementType("TYPE_ELEMENT");
  GrTypeParameterElementType TYPE_PARAMETER = new GrTypeParameterElementType("TYPE_PARAMETER");
  GrTypeParameterBoundsElementType TYPE_PARAMETER_BOUNDS_LIST = new GrTypeParameterBoundsElementType("TYPE_PARAMETER_BOUNDS_LIST");
  GrTypeParameterListElementType TYPE_PARAMETER_LIST = new GrTypeParameterListElementType("TYPE_PARAMETER_LIST");
  GroovyElementType UNARY_EXPRESSION = new GroovyElementType("UNARY_EXPRESSION");
  GrVariableElementType VARIABLE = new GrVariableElementType("VARIABLE");
  GrVariableDeclarationElementType VARIABLE_DECLARATION = new GrVariableDeclarationElementType("VARIABLE_DECLARATION");
  GroovyElementType WHILE_STATEMENT = new GroovyElementType("WHILE_STATEMENT");
  GroovyElementType WILDCARD_TYPE_ELEMENT = new GroovyElementType("WILDCARD_TYPE_ELEMENT");
  GroovyElementType XOR_EXPRESSION = new GroovyElementType("XOR_EXPRESSION");

  IElementType DOLLAR_SLASHY_BEGIN = new GroovyElementType("$/ regex begin");
  IElementType DOLLAR_SLASHY_CONTENT = new GroovyElementType("$/ regex content");
  IElementType DOLLAR_SLASHY_END = new GroovyElementType("$/ regex end");
  IElementType GSTRING_BEGIN = new GroovyElementType("Gstring begin");
  IElementType GSTRING_CONTENT = new GroovyElementType("Gstring content");
  IElementType GSTRING_END = new GroovyElementType("Gstring end");
  IElementType IDENTIFIER = new GroovyElementType("identifier");
  IElementType KW_ABSTRACT = new GroovyElementType("abstract");
  IElementType KW_AS = new GroovyElementType("as");
  IElementType KW_ASSERT = new GroovyElementType("assert");
  IElementType KW_BOOLEAN = new GroovyElementType("boolean");
  IElementType KW_BREAK = new GroovyElementType("break");
  IElementType KW_BYTE = new GroovyElementType("byte");
  IElementType KW_CASE = new GroovyElementType("case");
  IElementType KW_CATCH = new GroovyElementType("catch");
  IElementType KW_CHAR = new GroovyElementType("char");
  IElementType KW_CLASS = new GroovyElementType("class");
  IElementType KW_CONTINUE = new GroovyElementType("continue");
  IElementType KW_DEF = new GroovyElementType("def");
  IElementType KW_DEFAULT = new GroovyElementType("default");
  IElementType KW_DO = new GroovyElementType("do");
  IElementType KW_DOUBLE = new GroovyElementType("double");
  IElementType KW_ELSE = new GroovyElementType("else");
  IElementType KW_ENUM = new GroovyElementType("enum");
  IElementType KW_EXTENDS = new GroovyElementType("extends");
  IElementType KW_FALSE = new GroovyElementType("false");
  IElementType KW_FINAL = new GroovyElementType("final");
  IElementType KW_FINALLY = new GroovyElementType("finally");
  IElementType KW_FLOAT = new GroovyElementType("float");
  IElementType KW_FOR = new GroovyElementType("for");
  IElementType KW_IF = new GroovyElementType("if");
  IElementType KW_IMPLEMENTS = new GroovyElementType("implements");
  IElementType KW_IMPORT = new GroovyElementType("import");
  IElementType KW_IN = new GroovyElementType("in");
  IElementType KW_INSTANCEOF = new GroovyElementType("instanceof");
  IElementType KW_INT = new GroovyElementType("int");
  IElementType KW_INTERFACE = new GroovyElementType("interface");
  IElementType KW_LONG = new GroovyElementType("long");
  IElementType KW_NATIVE = new GroovyElementType("native");
  IElementType KW_NEW = new GroovyElementType("new");
  IElementType KW_NULL = new GroovyElementType("null");
  IElementType KW_PACKAGE = new GroovyElementType("package");
  IElementType KW_PRIVATE = new GroovyElementType("private");
  IElementType KW_PROTECTED = new GroovyElementType("protected");
  IElementType KW_PUBLIC = new GroovyElementType("public");
  IElementType KW_RETURN = new GroovyElementType("return");
  IElementType KW_SHORT = new GroovyElementType("short");
  IElementType KW_STATIC = new GroovyElementType("static");
  IElementType KW_STRICTFP = new GroovyElementType("strictfp");
  IElementType KW_SUPER = new GroovyElementType("super");
  IElementType KW_SWITCH = new GroovyElementType("switch");
  IElementType KW_SYNCHRONIZED = new GroovyElementType("synchronized");
  IElementType KW_THIS = new GroovyElementType("this");
  IElementType KW_THROW = new GroovyElementType("throw");
  IElementType KW_THROWS = new GroovyElementType("throws");
  IElementType KW_TRAIT = new GroovyElementType("trait");
  IElementType KW_TRANSIENT = new GroovyElementType("transient");
  IElementType KW_TRUE = new GroovyElementType("true");
  IElementType KW_TRY = new GroovyElementType("try");
  IElementType KW_VOID = new GroovyElementType("void");
  IElementType KW_VOLATILE = new GroovyElementType("volatile");
  IElementType KW_WHILE = new GroovyElementType("while");
  IElementType ML_COMMENT = new GroovyElementType("block comment");
  IElementType NL = new GroovyElementType("new line");
  IElementType NUM_BIG_DECIMAL = new GroovyElementType("BigDecimal");
  IElementType NUM_BIG_INT = new GroovyElementType("BigInteger");
  IElementType NUM_DOUBLE = new GroovyElementType("Double");
  IElementType NUM_FLOAT = new GroovyElementType("Float");
  IElementType NUM_INT = new GroovyElementType("Integer");
  IElementType NUM_LONG = new GroovyElementType("Long");
  IElementType SH_COMMENT = new GroovyElementType("shell comment");
  IElementType SLASHY_BEGIN = new GroovyElementType("regex begin");
  IElementType SLASHY_CONTENT = new GroovyElementType("regex content");
  IElementType SLASHY_END = new GroovyElementType("regex end");
  IElementType SL_COMMENT = new GroovyElementType("line comment");
  IElementType STR_DQ = new GroovyElementType("Gstring");
  IElementType STR_SQ = new GroovyElementType("string");
  IElementType T_ARROW = new GroovyElementType("->");
  IElementType T_ASSIGN = new GroovyElementType("=");
  IElementType T_AT = new GroovyElementType("@");
  IElementType T_BAND = new GroovyElementType("&");
  IElementType T_BAND_ASSIGN = new GroovyElementType("&=");
  IElementType T_BNOT = new GroovyElementType("~");
  IElementType T_BOR = new GroovyElementType("|");
  IElementType T_BOR_ASSIGN = new GroovyElementType("|=");
  IElementType T_COLON = new GroovyElementType(":");
  IElementType T_COMMA = new GroovyElementType(",");
  IElementType T_COMPARE = new GroovyElementType("<=>");
  IElementType T_DEC = new GroovyElementType("--");
  IElementType T_DIV = new GroovyElementType("/");
  IElementType T_DIV_ASSIGN = new GroovyElementType("/=");
  IElementType T_DOLLAR = new GroovyElementType("$");
  IElementType T_DOT = new GroovyElementType(".");
  IElementType T_ELLIPSIS = new GroovyElementType("...");
  IElementType T_ELVIS = new GroovyElementType("?:");
  IElementType T_ELVIS_ASSIGN = new GroovyElementType("?=");
  IElementType T_EQ = new GroovyElementType("==");
  IElementType T_GE = new GroovyElementType(">=");
  IElementType T_GT = new GroovyElementType(">");
  IElementType T_ID = new GroovyElementType("===");
  IElementType T_INC = new GroovyElementType("++");
  IElementType T_LAND = new GroovyElementType("&&");
  IElementType T_LBRACE = new GroovyElementType("{");
  IElementType T_LBRACK = new GroovyElementType("[");
  IElementType T_LE = new GroovyElementType("<=");
  IElementType T_LOR = new GroovyElementType("||");
  IElementType T_LPAREN = new GroovyElementType("(");
  IElementType T_LSH_ASSIGN = new GroovyElementType("<<=");
  IElementType T_LT = new GroovyElementType("<");
  IElementType T_METHOD_CLOSURE = new GroovyElementType(".&");
  IElementType T_MINUS = new GroovyElementType("-");
  IElementType T_MINUS_ASSIGN = new GroovyElementType("-=");
  IElementType T_NEQ = new GroovyElementType("!=");
  IElementType T_NID = new GroovyElementType("!==");
  IElementType T_NOT = new GroovyElementType("!");
  IElementType T_PLUS = new GroovyElementType("+");
  IElementType T_PLUS_ASSIGN = new GroovyElementType("+=");
  IElementType T_POW = new GroovyElementType("**");
  IElementType T_POW_ASSIGN = new GroovyElementType("**=");
  IElementType T_Q = new GroovyElementType("?");
  IElementType T_RANGE = new GroovyElementType("..");
  IElementType T_RANGE_EX = new GroovyElementType("..<");
  IElementType T_RBRACE = new GroovyElementType("}");
  IElementType T_RBRACK = new GroovyElementType("]");
  IElementType T_REGEX_FIND = new GroovyElementType("=~");
  IElementType T_REGEX_MATCH = new GroovyElementType("==~");
  IElementType T_REM = new GroovyElementType("%");
  IElementType T_REM_ASSIGN = new GroovyElementType("%=");
  IElementType T_RPAREN = new GroovyElementType(")");
  IElementType T_RSHU_ASSIGN = new GroovyElementType(">>>=");
  IElementType T_RSH_ASSIGN = new GroovyElementType(">>=");
  IElementType T_SAFE_DOT = new GroovyElementType("?.");
  IElementType T_SEMI = new GroovyElementType(";");
  IElementType T_SPREAD_DOT = new GroovyElementType("*.");
  IElementType T_STAR = new GroovyElementType("*");
  IElementType T_STAR_ASSIGN = new GroovyElementType("*=");
  IElementType T_WRONG = new GroovyElementType("wrong token");
  IElementType T_XOR = new GroovyElementType("^");
  IElementType T_XOR_ASSIGN = new GroovyElementType("^=");
}
