import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './fim-accounts.reducer';

export const FimAccounts = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const fimAccountsList = useAppSelector(state => state.fim.fimAccounts.entities);
  const loading = useAppSelector(state => state.fim.fimAccounts.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="fim-accounts-heading" data-cy="FimAccountsHeading">
        <Translate contentKey="fimApp.fimFimAccounts.home.title">Fim Accounts</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="fimApp.fimFimAccounts.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/fim/fim-accounts/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="fimApp.fimFimAccounts.home.createLabel">Create new Fim Accounts</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {fimAccountsList && fimAccountsList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="fimApp.fimFimAccounts.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('accountId')}>
                  <Translate contentKey="fimApp.fimFimAccounts.accountId">Account Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('accountId')} />
                </th>
                <th className="hand" onClick={sort('custId')}>
                  <Translate contentKey="fimApp.fimFimAccounts.custId">Cust Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('custId')} />
                </th>
                <th className="hand" onClick={sort('relnId')}>
                  <Translate contentKey="fimApp.fimFimAccounts.relnId">Reln Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('relnId')} />
                </th>
                <th className="hand" onClick={sort('relnType')}>
                  <Translate contentKey="fimApp.fimFimAccounts.relnType">Reln Type</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('relnType')} />
                </th>
                <th className="hand" onClick={sort('operInst')}>
                  <Translate contentKey="fimApp.fimFimAccounts.operInst">Oper Inst</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('operInst')} />
                </th>
                <th className="hand" onClick={sort('isAcctNbr')}>
                  <Translate contentKey="fimApp.fimFimAccounts.isAcctNbr">Is Acct Nbr</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isAcctNbr')} />
                </th>
                <th className="hand" onClick={sort('bndAcctNbr')}>
                  <Translate contentKey="fimApp.fimFimAccounts.bndAcctNbr">Bnd Acct Nbr</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('bndAcctNbr')} />
                </th>
                <th className="hand" onClick={sort('closingId')}>
                  <Translate contentKey="fimApp.fimFimAccounts.closingId">Closing Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('closingId')} />
                </th>
                <th className="hand" onClick={sort('subSegment')}>
                  <Translate contentKey="fimApp.fimFimAccounts.subSegment">Sub Segment</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('subSegment')} />
                </th>
                <th className="hand" onClick={sort('branchCode')}>
                  <Translate contentKey="fimApp.fimFimAccounts.branchCode">Branch Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('branchCode')} />
                </th>
                <th className="hand" onClick={sort('acctStatus')}>
                  <Translate contentKey="fimApp.fimFimAccounts.acctStatus">Acct Status</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('acctStatus')} />
                </th>
                <th className="hand" onClick={sort('ctryCode')}>
                  <Translate contentKey="fimApp.fimFimAccounts.ctryCode">Ctry Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('ctryCode')} />
                </th>
                <th className="hand" onClick={sort('acctOwners')}>
                  <Translate contentKey="fimApp.fimFimAccounts.acctOwners">Acct Owners</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('acctOwners')} />
                </th>
                <th className="hand" onClick={sort('remarks')}>
                  <Translate contentKey="fimApp.fimFimAccounts.remarks">Remarks</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('remarks')} />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="fimApp.fimFimAccounts.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                </th>
                <th className="hand" onClick={sort('createdTs')}>
                  <Translate contentKey="fimApp.fimFimAccounts.createdTs">Created Ts</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdTs')} />
                </th>
                <th className="hand" onClick={sort('updatedBy')}>
                  <Translate contentKey="fimApp.fimFimAccounts.updatedBy">Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                </th>
                <th className="hand" onClick={sort('updatedTs')}>
                  <Translate contentKey="fimApp.fimFimAccounts.updatedTs">Updated Ts</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedTs')} />
                </th>
                <th className="hand" onClick={sort('recordStatus')}>
                  <Translate contentKey="fimApp.fimFimAccounts.recordStatus">Record Status</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('recordStatus')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {fimAccountsList.map((fimAccounts, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/fim/fim-accounts/${fimAccounts.id}`} color="link" size="sm">
                      {fimAccounts.id}
                    </Button>
                  </td>
                  <td>{fimAccounts.accountId}</td>
                  <td>{fimAccounts.custId}</td>
                  <td>{fimAccounts.relnId}</td>
                  <td>{fimAccounts.relnType}</td>
                  <td>{fimAccounts.operInst}</td>
                  <td>{fimAccounts.isAcctNbr}</td>
                  <td>{fimAccounts.bndAcctNbr}</td>
                  <td>{fimAccounts.closingId}</td>
                  <td>{fimAccounts.subSegment}</td>
                  <td>{fimAccounts.branchCode}</td>
                  <td>{fimAccounts.acctStatus}</td>
                  <td>{fimAccounts.ctryCode}</td>
                  <td>{fimAccounts.acctOwners}</td>
                  <td>{fimAccounts.remarks}</td>
                  <td>{fimAccounts.createdBy}</td>
                  <td>
                    {fimAccounts.createdTs ? <TextFormat type="date" value={fimAccounts.createdTs} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{fimAccounts.updatedBy}</td>
                  <td>
                    {fimAccounts.updatedTs ? <TextFormat type="date" value={fimAccounts.updatedTs} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{fimAccounts.recordStatus}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/fim/fim-accounts/${fimAccounts.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/fim/fim-accounts/${fimAccounts.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/fim/fim-accounts/${fimAccounts.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="fimApp.fimFimAccounts.home.notFound">No Fim Accounts found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default FimAccounts;
