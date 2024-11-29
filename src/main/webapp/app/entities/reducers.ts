import fimAccounts from 'app/entities/FIM/fim-accounts/fim-accounts.reducer';
import fimAccountsWq from 'app/entities/FIM/fim-accounts-wq/fim-accounts-wq.reducer';
import fimAccountsHistory from 'app/entities/FIM/fim-accounts-history/fim-accounts-history.reducer';
import fimSettAcct from 'app/entities/FIM/fim-sett-acct/fim-sett-acct.reducer';
import fimSettAcctWq from 'app/entities/FIM/fim-sett-acct-wq/fim-sett-acct-wq.reducer';
import fimSettAcctHistory from 'app/entities/FIM/fim-sett-acct-history/fim-sett-acct-history.reducer';
import fimCust from 'app/entities/FIM/fim-cust/fim-cust.reducer';
import fimCustWq from 'app/entities/FIM/fim-cust-wq/fim-cust-wq.reducer';
import fimCustHistory from 'app/entities/FIM/fim-cust-history/fim-cust-history.reducer';
import ethnicity from 'app/entities/FIM/ethnicity/ethnicity.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  fimAccounts,
  fimAccountsWq,
  fimAccountsHistory,
  fimSettAcct,
  fimSettAcctWq,
  fimSettAcctHistory,
  fimCust,
  fimCustWq,
  fimCustHistory,
  ethnicity,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
