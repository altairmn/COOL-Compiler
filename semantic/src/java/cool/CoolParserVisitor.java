// Generated from CoolParser.g4 by ANTLR 4.5.1
package cool;

	import cool.AST;
	import java.util.List;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CoolParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CoolParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CoolParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CoolParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#class_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_list(CoolParser.Class_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#class_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_(CoolParser.Class_Context ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#feature_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeature_list(CoolParser.Feature_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#feature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeature(CoolParser.FeatureContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#formal_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormal_list(CoolParser.Formal_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#formal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormal(CoolParser.FormalContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(CoolParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#expression_list_actual}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_list_actual(CoolParser.Expression_list_actualContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#expression_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_list(CoolParser.Expression_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#case_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCase_list(CoolParser.Case_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#case_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCase_(CoolParser.Case_Context ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#let_looper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLet_looper(CoolParser.Let_looperContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#star_slash}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStar_slash(CoolParser.Star_slashContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#plus_minus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlus_minus(CoolParser.Plus_minusContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#lt_le_equals}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLt_le_equals(CoolParser.Lt_le_equalsContext ctx);
}