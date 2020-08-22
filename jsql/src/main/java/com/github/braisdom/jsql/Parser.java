/* Parser.java */
/* Generated By:JavaCC: Do not edit this line. Parser.java */
package com.github.braisdom.jsql;

import java.util.*;
import com.github.braisdom.jsql.ast.*;

public final class Parser implements ParserConstants {

    public static String unquote(String value, String quote) {
        if (value.startsWith(quote) && (value.endsWith(quote))) {
            return value.substring(1, value.length() - 1);
        }
        return value;
    }

  final public JSqlNode JSqlNode() throws ParseException {final JSqlNode jsqlNode = new JSqlNode();
    ImportNode importNode;
    DatasetNode datasetNode;
    label_1:
    while (true) {
      if (jj_2_1(4)) {
        ;
      } else {
        break label_1;
      }
      if (jj_2_2(4)) {
        importNode = ImportNode();
jsqlNode.addImportNode(importNode);
      } else if (jj_2_3(4)) {
        datasetNode = DatasetNode();
jsqlNode.addDatasetNode(datasetNode);
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
{if ("" != null) return jsqlNode;}
    throw new Error("Missing return statement in function");
}

  final public ImportNode ImportNode() throws ParseException {ImportNode importNode;
    String importDeclaration;
    jj_consume_token(IMPORT);
    importDeclaration = Name();
importNode = new ImportNode(importDeclaration);
    jj_consume_token(SEMICOLON);
{if ("" != null) return importNode;}
    throw new Error("Missing return statement in function");
}

  final public DatasetNode DatasetNode() throws ParseException {final DatasetNode datasetNode = new DatasetNode();
    FormalParameterNode parameter;
    Projectional projectional;
    jj_consume_token(TYPEDEF);
    jj_consume_token(DATASET);
    jj_consume_token(IDENTIFIER);
datasetNode.setName(getToken(0).image);
    jj_consume_token(LPAREN);
    parameter = FormalParameterNode();
datasetNode.addFormalParameter(parameter);
    label_2:
    while (true) {
      if (jj_2_4(4)) {
        ;
      } else {
        break label_2;
      }
      jj_consume_token(COMMA);
      parameter = FormalParameterNode();
datasetNode.addFormalParameter(parameter);
    }
    jj_consume_token(RPAREN);
    jj_consume_token(LBRACE);
    jj_consume_token(PROJECTION);
    jj_consume_token(LBRACKET);
    Projectional(datasetNode);
    label_3:
    while (true) {
      if (jj_2_5(4)) {
        ;
      } else {
        break label_3;
      }
      jj_consume_token(COMMA);
      Projectional(datasetNode);
    }
    jj_consume_token(RBRACKET);
    jj_consume_token(RBRACE);
{if ("" != null) return datasetNode;}
    throw new Error("Missing return statement in function");
}

  final public FormalParameterNode FormalParameterNode() throws ParseException {String type;
    String name;
    jj_consume_token(IDENTIFIER);
type = getToken(0).image;
    jj_consume_token(IDENTIFIER);
name = getToken(0).image;
{if ("" != null) return new FormalParameterNode(type, name);}
    throw new Error("Missing return statement in function");
}

  final public void Projectional(DatasetNode datasetNode) throws ParseException {Projectional projectional = null;
    Token token;
    if (jj_2_11(4)) {
      if (jj_2_8(4)) {
        projectional = SymbolNode();
        if (jj_2_6(4)) {
          jj_consume_token(AS);
          token = jj_consume_token(IDENTIFIER);
projectional.setAlias(token.image);
        } else {
          ;
        }
      } else if (jj_2_9(4)) {
        projectional = SqlLiteral();
        if (jj_2_7(4)) {
          jj_consume_token(AS);
          token = jj_consume_token(IDENTIFIER);
projectional.setAlias(token.image);
        } else {
          ;
        }
      } else if (jj_2_10(4)) {
        projectional = SqlFunctionCallNode();
        jj_consume_token(AS);
        token = jj_consume_token(IDENTIFIER);
projectional.setAlias(token.image);
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
    } else {
      ;
    }
datasetNode.addProjectional(projectional);
}

  final public SqlFunctionCallNode SqlFunctionCallNode() throws ParseException {SqlFunctionCallNode sqlFunctionCallNode = new SqlFunctionCallNode();
    SqlFunctionOperand operand;
    if (jj_2_12(4)) {
      jj_consume_token(IDENTIFIER);
sqlFunctionCallNode.setType(token.image);
      jj_consume_token(DOT);
    } else {
      ;
    }
    jj_consume_token(IDENTIFIER);
sqlFunctionCallNode.setName(getToken(0).image);
    jj_consume_token(LPAREN);
    if (jj_2_14(4)) {
      operand = SqlFunctionOperand();
sqlFunctionCallNode.addOperands(operand);
      label_4:
      while (true) {
        if (jj_2_13(4)) {
          ;
        } else {
          break label_4;
        }
        jj_consume_token(COMMA);
        operand = SqlFunctionOperand();
sqlFunctionCallNode.addOperands(operand);
      }
    } else {
      ;
    }
    jj_consume_token(RPAREN);
{if ("" != null) return sqlFunctionCallNode;}
    throw new Error("Missing return statement in function");
}

  final public SqlFunctionOperand SqlFunctionOperand() throws ParseException {SqlFunctionOperand operand;
    if (jj_2_15(4)) {
      operand = SqlLiteral();
{if ("" != null) return operand;}
    } else if (jj_2_16(2147483647)) {
      operand = IsNullOperatorNode();
{if ("" != null) return operand;}
    } else if (jj_2_17(4)) {
      operand = SymbolNode();
{if ("" != null) return operand;}
    } else if (jj_2_18(4)) {
      operand = SqlFunctionCallNode();
{if ("" != null) return operand;}
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  final public SqlLiteral SqlLiteral() throws ParseException {Object value = null;
    Token token;
    if (jj_2_19(4)) {
      token = jj_consume_token(STRINGVAL);
value = token.image;
    } else if (jj_2_20(4)) {
      token = jj_consume_token(DECIMAL_LITERAL);
value = Integer.parseInt(token.image);
    } else if (jj_2_21(4)) {
      token = jj_consume_token(FLOATING_LITERAL);
value = Float.parseFloat(token.image);
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return new SqlLiteral(value);}
    throw new Error("Missing return statement in function");
}

  final public SymbolNode SymbolNode() throws ParseException {SymbolNode symbolNode = new SymbolNode();
    if (jj_2_22(4)) {
      jj_consume_token(COLON);
      jj_consume_token(IDENTIFIER);
symbolNode.setSymbolName(token.image);
    } else if (jj_2_23(4)) {
      jj_consume_token(55);
      jj_consume_token(IDENTIFIER);
symbolNode.setDatasetName(token.image);
      jj_consume_token(DOT);
      jj_consume_token(IDENTIFIER);
symbolNode.setSymbolName(token.image);
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return symbolNode;}
    throw new Error("Missing return statement in function");
}

  final public IsNullOperatorNode IsNullOperatorNode() throws ParseException {IsNullOperatorNode isNullOperatorNode = new IsNullOperatorNode();
    SymbolNode symbolNode;
    symbolNode = SymbolNode();
isNullOperatorNode.setSymbol(symbolNode);
    if (jj_2_26(4)) {
      if (jj_2_24(4)) {
        jj_consume_token(IS_NULL);
isNullOperatorNode.setNull(true);
      } else if (jj_2_25(4)) {
        jj_consume_token(IS_NOT_NULL);
isNullOperatorNode.setNull(false);
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
    } else {
      ;
    }
{if ("" != null) return isNullOperatorNode;}
    throw new Error("Missing return statement in function");
}

  final public String Name() throws ParseException {List<String> names = new ArrayList();
    jj_consume_token(IDENTIFIER);
names.add(getToken(0).image);
    label_5:
    while (true) {
      if (jj_2_27(4)) {
        ;
      } else {
        break label_5;
      }
      jj_consume_token(DOT);
      jj_consume_token(IDENTIFIER);
names.add(getToken(0).image);
    }
{if ("" != null) return String.join(".", names.toArray(new String[0]));}
    throw new Error("Missing return statement in function");
}

  private boolean jj_2_1(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_1()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_2()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_3()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_2_4(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_4()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  private boolean jj_2_5(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_5()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  private boolean jj_2_6(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_6()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  private boolean jj_2_7(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_7()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(6, xla); }
  }

  private boolean jj_2_8(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_8()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(7, xla); }
  }

  private boolean jj_2_9(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_9()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(8, xla); }
  }

  private boolean jj_2_10(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_10()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(9, xla); }
  }

  private boolean jj_2_11(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_11()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(10, xla); }
  }

  private boolean jj_2_12(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_12()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(11, xla); }
  }

  private boolean jj_2_13(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_13()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(12, xla); }
  }

  private boolean jj_2_14(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_14()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(13, xla); }
  }

  private boolean jj_2_15(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_15()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(14, xla); }
  }

  private boolean jj_2_16(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_16()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(15, xla); }
  }

  private boolean jj_2_17(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_17()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(16, xla); }
  }

  private boolean jj_2_18(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_18()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(17, xla); }
  }

  private boolean jj_2_19(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_19()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(18, xla); }
  }

  private boolean jj_2_20(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_20()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(19, xla); }
  }

  private boolean jj_2_21(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_21()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(20, xla); }
  }

  private boolean jj_2_22(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_22()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(21, xla); }
  }

  private boolean jj_2_23(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_23()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(22, xla); }
  }

  private boolean jj_2_24(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_24()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(23, xla); }
  }

  private boolean jj_2_25(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_25()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(24, xla); }
  }

  private boolean jj_2_26(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_26()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(25, xla); }
  }

  private boolean jj_2_27(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_27()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(26, xla); }
  }

  private boolean jj_3_10()
 {
    if (jj_3R_SqlFunctionCallNode_185_5_12()) return true;
    if (jj_scan_token(AS)) return true;
    return false;
  }

  private boolean jj_3_9()
 {
    if (jj_3R_SqlLiteral_216_5_11()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_7()) jj_scanpos = xsp;
    return false;
  }

  private boolean jj_3_11()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_8()) {
    jj_scanpos = xsp;
    if (jj_3_9()) {
    jj_scanpos = xsp;
    if (jj_3_10()) return true;
    }
    }
    return false;
  }

  private boolean jj_3_8()
 {
    if (jj_3R_SymbolNode_227_5_10()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_6()) jj_scanpos = xsp;
    return false;
  }

  private boolean jj_3R_Projectional_172_5_9()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_11()) jj_scanpos = xsp;
    return false;
  }

  private boolean jj_3R_FormalParameterNode_162_5_8()
 {
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  private boolean jj_3_4()
 {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_FormalParameterNode_162_5_8()) return true;
    return false;
  }

  private boolean jj_3R_Name_252_3_15()
 {
    if (jj_scan_token(IDENTIFIER)) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_27()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3_25()
 {
    if (jj_scan_token(IS_NOT_NULL)) return true;
    return false;
  }

  private boolean jj_3_24()
 {
    if (jj_scan_token(IS_NULL)) return true;
    return false;
  }

  private boolean jj_3_26()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_24()) {
    jj_scanpos = xsp;
    if (jj_3_25()) return true;
    }
    return false;
  }

  private boolean jj_3R_IsNullOperatorNode_240_5_14()
 {
    if (jj_3R_SymbolNode_227_5_10()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_26()) jj_scanpos = xsp;
    return false;
  }

  private boolean jj_3R_DatasetNode_143_5_7()
 {
    if (jj_scan_token(TYPEDEF)) return true;
    if (jj_scan_token(DATASET)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(LPAREN)) return true;
    return false;
  }

  private boolean jj_3_23()
 {
    if (jj_scan_token(55)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(DOT)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  private boolean jj_3_22()
 {
    if (jj_scan_token(COLON)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  private boolean jj_3R_SymbolNode_227_5_10()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_22()) {
    jj_scanpos = xsp;
    if (jj_3_23()) return true;
    }
    return false;
  }

  private boolean jj_3R_ImportNode_130_5_6()
 {
    if (jj_scan_token(IMPORT)) return true;
    if (jj_3R_Name_252_3_15()) return true;
    if (jj_scan_token(SEMICOLON)) return true;
    return false;
  }

  private boolean jj_3_21()
 {
    if (jj_scan_token(FLOATING_LITERAL)) return true;
    return false;
  }

  private boolean jj_3_3()
 {
    if (jj_3R_DatasetNode_143_5_7()) return true;
    return false;
  }

  private boolean jj_3_16()
 {
    if (jj_3R_IsNullOperatorNode_240_5_14()) return true;
    return false;
  }

  private boolean jj_3_20()
 {
    if (jj_scan_token(DECIMAL_LITERAL)) return true;
    return false;
  }

  private boolean jj_3_1()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_2()) {
    jj_scanpos = xsp;
    if (jj_3_3()) return true;
    }
    return false;
  }

  private boolean jj_3_2()
 {
    if (jj_3R_ImportNode_130_5_6()) return true;
    return false;
  }

  private boolean jj_3_19()
 {
    if (jj_scan_token(STRINGVAL)) return true;
    return false;
  }

  private boolean jj_3R_SqlLiteral_216_5_11()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_19()) {
    jj_scanpos = xsp;
    if (jj_3_20()) {
    jj_scanpos = xsp;
    if (jj_3_21()) return true;
    }
    }
    return false;
  }

  private boolean jj_3_18()
 {
    if (jj_3R_SqlFunctionCallNode_185_5_12()) return true;
    return false;
  }

  private boolean jj_3_17()
 {
    if (jj_3R_SymbolNode_227_5_10()) return true;
    return false;
  }

  private boolean jj_3_7()
 {
    if (jj_scan_token(AS)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  private boolean jj_3R_SqlFunctionOperand_204_9_16()
 {
    if (jj_3R_IsNullOperatorNode_240_5_14()) return true;
    return false;
  }

  private boolean jj_3_6()
 {
    if (jj_scan_token(AS)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  private boolean jj_3_15()
 {
    if (jj_3R_SqlLiteral_216_5_11()) return true;
    return false;
  }

  private boolean jj_3_27()
 {
    if (jj_scan_token(DOT)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  private boolean jj_3_13()
 {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_SqlFunctionOperand_202_5_13()) return true;
    return false;
  }

  private boolean jj_3_5()
 {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_Projectional_172_5_9()) return true;
    return false;
  }

  private boolean jj_3_14()
 {
    if (jj_3R_SqlFunctionOperand_202_5_13()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_13()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_SqlFunctionOperand_202_5_13()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_15()) {
    jj_scanpos = xsp;
    if (jj_3R_SqlFunctionOperand_204_9_16()) {
    jj_scanpos = xsp;
    if (jj_3_17()) {
    jj_scanpos = xsp;
    if (jj_3_18()) return true;
    }
    }
    }
    return false;
  }

  private boolean jj_3_12()
 {
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(DOT)) return true;
    return false;
  }

  private boolean jj_3R_SqlFunctionCallNode_185_5_12()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_12()) jj_scanpos = xsp;
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(LPAREN)) return true;
    xsp = jj_scanpos;
    if (jj_3_14()) jj_scanpos = xsp;
    if (jj_scan_token(RPAREN)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public ParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[0];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
	   jj_la1_init_0();
	   jj_la1_init_1();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {};
	}
	private static void jj_la1_init_1() {
	   jj_la1_1 = new int[] {};
	}
  final private JJCalls[] jj_2_rtns = new JJCalls[27];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new ParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 0; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new ParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new ParserTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   if (++jj_gc > 100) {
		 jj_gc = 0;
		 for (int i = 0; i < jj_2_rtns.length; i++) {
		   JJCalls c = jj_2_rtns[i];
		   while (c != null) {
			 if (c.gen < jj_gen) c.first = null;
			 c = c.next;
		   }
		 }
	   }
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }

  @SuppressWarnings("serial")
  static private final class LookaheadSuccess extends java.lang.Error {
    @Override
    public Throwable fillInStackTrace() {
      return this;
    }
  }
  static private final LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
	 if (jj_scanpos == jj_lastpos) {
	   jj_la--;
	   if (jj_scanpos.next == null) {
		 jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
	   } else {
		 jj_lastpos = jj_scanpos = jj_scanpos.next;
	   }
	 } else {
	   jj_scanpos = jj_scanpos.next;
	 }
	 if (jj_rescan) {
	   int i = 0; Token tok = token;
	   while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
	   if (tok != null) jj_add_error_token(kind, i);
	 }
	 if (jj_scanpos.kind != kind) return true;
	 if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
	 return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	 return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
	 if (pos >= 100) {
		return;
	 }

	 if (pos == jj_endpos + 1) {
	   jj_lasttokens[jj_endpos++] = kind;
	 } else if (jj_endpos != 0) {
	   jj_expentry = new int[jj_endpos];

	   for (int i = 0; i < jj_endpos; i++) {
		 jj_expentry[i] = jj_lasttokens[i];
	   }

	   for (int[] oldentry : jj_expentries) {
		 if (oldentry.length == jj_expentry.length) {
		   boolean isMatched = true;

		   for (int i = 0; i < jj_expentry.length; i++) {
			 if (oldentry[i] != jj_expentry[i]) {
			   isMatched = false;
			   break;
			 }

		   }
		   if (isMatched) {
			 jj_expentries.add(jj_expentry);
			 break;
		   }
		 }
	   }

	   if (pos != 0) {
		 jj_lasttokens[(jj_endpos = pos) - 1] = kind;
	   }
	 }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[56];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 0; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		   if ((jj_la1_1[i] & (1<<j)) != 0) {
			 la1tokens[32+j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 56; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 jj_endpos = 0;
	 jj_rescan_token();
	 jj_add_error_token(0, 0);
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  private boolean trace_enabled;

/** Trace enabled. */
  final public boolean trace_enabled() {
	 return trace_enabled;
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
	 jj_rescan = true;
	 for (int i = 0; i < 27; i++) {
	   try {
		 JJCalls p = jj_2_rtns[i];

		 do {
		   if (p.gen > jj_gen) {
			 jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
			 switch (i) {
			   case 0: jj_3_1(); break;
			   case 1: jj_3_2(); break;
			   case 2: jj_3_3(); break;
			   case 3: jj_3_4(); break;
			   case 4: jj_3_5(); break;
			   case 5: jj_3_6(); break;
			   case 6: jj_3_7(); break;
			   case 7: jj_3_8(); break;
			   case 8: jj_3_9(); break;
			   case 9: jj_3_10(); break;
			   case 10: jj_3_11(); break;
			   case 11: jj_3_12(); break;
			   case 12: jj_3_13(); break;
			   case 13: jj_3_14(); break;
			   case 14: jj_3_15(); break;
			   case 15: jj_3_16(); break;
			   case 16: jj_3_17(); break;
			   case 17: jj_3_18(); break;
			   case 18: jj_3_19(); break;
			   case 19: jj_3_20(); break;
			   case 20: jj_3_21(); break;
			   case 21: jj_3_22(); break;
			   case 22: jj_3_23(); break;
			   case 23: jj_3_24(); break;
			   case 24: jj_3_25(); break;
			   case 25: jj_3_26(); break;
			   case 26: jj_3_27(); break;
			 }
		   }
		   p = p.next;
		 } while (p != null);

		 } catch(LookaheadSuccess ls) { }
	 }
	 jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
	 JJCalls p = jj_2_rtns[index];
	 while (p.gen > jj_gen) {
	   if (p.next == null) { p = p.next = new JJCalls(); break; }
	   p = p.next;
	 }

	 p.gen = jj_gen + xla - jj_la; 
	 p.first = token;
	 p.arg = xla;
  }

  static final class JJCalls {
	 int gen;
	 Token first;
	 int arg;
	 JJCalls next;
  }

}