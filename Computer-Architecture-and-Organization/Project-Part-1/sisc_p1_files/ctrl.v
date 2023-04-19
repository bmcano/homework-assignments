// ECE:3350 SISC computer project
// finite state machine

`timescale 1ns/100ps

module ctrl (clk, rst_f, opcode, mm, stat, rf_we, alu_op, wb_sel);

  /* Declare the ports listed above as inputs or outputs.*/
  
  input clk, rst_f;
  input [3:0] opcode, mm, stat;
  output reg rf_we, wb_sel;
  output reg [1:0] alu_op;
  
  // state parameter declarations
  parameter start0 = 0, start1 = 1, fetch = 2, decode = 3, execute = 4, mem = 5, writeback = 6;
   
  // opcode paramenter declarations
  parameter NOOP = 0, LOD = 1, STR = 2, SWP = 3, BRA = 4, BRR = 5, BNE = 6, BNR = 7, ALU_OP = 8, HLT=15;

  // addressing modes
  parameter AM_IMM = 8;

  // state register and next state value
  reg [2:0]  present_state, next_state;

  // Initialize present state to 'start0'.
  initial
    present_state = start0;

  /* Clock procedure that progresses the fsm to the next state on the positive 
     edge of the clock, OR resets the state to 'start1' on the negative edge
     of rst_f. Notice that the computer is reset when rst_f is low, not high. */

  always @(posedge clk, negedge rst_f)
  begin
    if (rst_f == 1'b0)
      present_state <= start1;
    else
      present_state <= next_state;
  end
  
  /* Combinational procedure that determines the next state of the fsm. */

  always @(present_state, rst_f)
  begin
    case(present_state)
      start0:
        next_state = start1;
      start1:
	if (rst_f == 1'b0) 
          next_state = start1;
	else
          next_state = fetch;
      fetch:
        next_state = decode;
      decode:
        next_state = execute;
      execute:
        next_state = mem;
      mem:
        next_state = writeback;
      writeback:
        next_state = fetch;
      default:
        next_state = start1;
    endcase
  end
  

  /* Generate the rf_we, alu_op, wb_sel outputs based on the FSM states 
     and inputs. */

  always @(present_state, opcode, mm)
  begin
    /* TODO: Put your default assignments for wb_sel, rf_we, and alu_op here.  */
    wb_sel = 1'b0;
    rf_we = 1'b0;
    alu_op = 2'b10;
  
    case(present_state)

      execute:
      begin
        /* TODO: Make assignments to alu_op based upon the opcode and mm values.*/
	if (opcode == 4'b1000 && mm == 4'b1000)
	  alu_op = 2'b01;
        else
    	  alu_op = 2'b00;
      end

      mem:
      begin
        /* TODO: Make assignments to alu_op based upon the opcode and mm values. 
           Note that the ALU operation must be valid at the end of the mem 
           stage so the correct value can be written to the register file. */
        if (opcode == 4'b1000 && mm == 4'b1000)
	  alu_op = 2'b11;
        else
    	  alu_op = 2'b10;
      end

      writeback:
      begin
        /* TODO: Make the assignment to rf_we here conditional on the opcode = 
           ALU_OP.  We don't want to write to the register file if the 
           instruction is a NOOP. */
        if (opcode == 4'b1000)
          rf_we = 1'b1;
        else
          rf_we = 1'b0;
      end

      default:
      begin
        /* TODO: Put your default assignments for wb_sel, rf_we, and alu_op here.  */
        wb_sel = 1'b0;
        rf_we = 1'b0;
        alu_op = 2'b10;
      end
    endcase
  end

// Halt on HLT instruction  
  always @(opcode)
  begin
    if (opcode == HLT)
    begin 
      #5 $display ("Halt."); //Delay 5 ns so $monitor will print the halt instruction
      $stop;
    end
  end
    
  
endmodule
