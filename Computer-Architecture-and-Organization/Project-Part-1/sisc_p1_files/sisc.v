// ECE:3350 SISC processor project
// main SISC module, part 1

`timescale 1ns/100ps  

module sisc (clk, rst_f, ir);

  input clk, rst_f;
  input [31:0] ir;

  // TODO: declare all internal wires here
  wire wb_sel;
  wire rf_we;
  wire stat_en;
  wire [3:0] stat;
  wire [3:0] mux4_out;
  wire [31:0] mux32_out;
  wire [31:0] rsa;
  wire [31:0] rsb;
  wire [1:0] alu_op;
  wire [3:0] alu_stat;
  wire [31:0] alu_result;
 

  // TODO: component instantiation goes here
  mux4 u1(ir[15:12], ir[23:20], 1'b0, mux4_out);

  ctrl u3(clk, rst_f, ir[31:28], ir[27:24], stat, rf_we, alu_op, wb_sel);

  rf u2(clk, ir[19:16], mux4_out, ir[23:20], mux32_out, rf_we, rsa, rsb);

  statreg u4(clk, alu_stat, stat_en, stat);

  alu u5(clk, rsa, rsb, ir[15:0], alu_op, alu_result, alu_stat, stat_en);

  mux32 u6(alu_result, 0, wb_sel, mux32_out);

  initial
  $monitor($time,,"%h  %h  %h  %h  %h  %h  %b  %b  %b  %b",ir,u2.ram_array[1],u2.ram_array[2],u2.ram_array[3],u2.ram_array[4],u2.ram_array[5],alu_op,wb_sel,rf_we,stat);



endmodule


