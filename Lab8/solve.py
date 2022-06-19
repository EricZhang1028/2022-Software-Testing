import angr
import claripy
from angrutils import *

proj = angr.Project("./target")

main = proj.loader.main_object.get_symbol("main")
start_state = proj.factory.blank_state(addr=main.rebased_addr)
cfg = proj.analyses.CFGEmulated(fail_fast=True, starts=[main.rebased_addr], initial_state=start_state)
plot_cfg(cfg, "target", remove_imports=True, remove_path_terminator=True)

########################################################

sym_arg_size = 28
sym_arg = claripy.BVS("sym_arg", 8*sym_arg_size)

argv = [proj.filename]
argv.append(sym_arg)
state = proj.factory.entry_state(args=argv)

simgr = proj.factory.simulation_manager(state)

# avoid_addr = []
simgr.explore(find=0x401060)

found = simgr.found[0]
print(found.solver.eval(sym_arg, cast_to=bytes))