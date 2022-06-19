
#include "llvm/Analysis/CFGPrinter.h"
#include "llvm/IR/IRBuilder.h"
#include "llvm/IR/Function.h"
#include "llvm/IR/LegacyPassManager.h"
#include "llvm/IR/Module.h"
#include "llvm/Support/FileSystem.h"
#include "llvm/Support/raw_ostream.h"
#include "llvm/Transforms/IPO/PassManagerBuilder.h"
#include "llvm/Pass.h"
// #include "llvm/IR/Type.h"

using namespace llvm;

namespace {

class ExamplePass : public ModulePass {

 public:
  static char ID;
  ExamplePass() : ModulePass(ID) { }
  
  bool doInitialization(Module &M) override;
  bool runOnModule(Module &M) override;

};

}  // namespace

char ExamplePass::ID = 0;

bool ExamplePass::doInitialization(Module &M) {

  return true;

}

bool ExamplePass::runOnModule(Module &M) {
  IntegerType *Int32Ty = IntegerType::getInt32Ty(M.getContext());
  Type *CharTy = Type::getInt8PtrTy(M.getContext());
  Type *VoidTy = Type::getVoidTy(M.getContext());

  FunctionType *dbgFnTy = FunctionType::get(VoidTy, {Int32Ty}, false);
  FunctionCallee dbgFn = M.getOrInsertFunction("debug", dbgFnTy);
  
  // errs() << "runOnModule\n";
  
  for (auto &F : M) {    
    
    /* add you code here */
    // errs() << F.getName() << "\n";

    if(F.getName() == "main"){

      // case_a
      BasicBlock &BB = F.getEntryBlock();
      BasicBlock::iterator IP = BB.getFirstInsertionPt();
      IRBuilder<> IRB(&(*IP));

      IRB.CreateCall(dbgFn, ConstantInt::get(Int32Ty, 9527));

      // case_b
      Value *Argv1 = IRB.CreateGlobalStringPtr("aesophor is ghost !!!");
      Value *Argv1Target = IRB.CreateGEP(CharTy, F.getArg(1), ConstantInt::get(Int32Ty, 1));
      IRB.CreateStore(Argv1, Argv1Target);

      // case_c
      F.getArg(0)->replaceAllUsesWith(ConstantInt::get(Int32Ty, 9487));
    }
    
  }

  return true;
  
}

static void registerExamplePass(const PassManagerBuilder &,
                                           legacy::PassManagerBase &PM) {

  PM.add(new ExamplePass());

}

static RegisterStandardPasses RegisterExamplePass(
    PassManagerBuilder::EP_OptimizerLast, registerExamplePass);

static RegisterStandardPasses RegisterExamplePass0(
    PassManagerBuilder::EP_EnabledOnOptLevel0, registerExamplePass);

