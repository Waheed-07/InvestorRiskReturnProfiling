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

import { getEntities } from './fim-sett-acct-history.reducer';

export const FimSettAcctHistory = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const fimSettAcctHistoryList = useAppSelector(state => state.fim.fimSettAcctHistory.entities);
  const loading = useAppSelector(state => state.fim.fimSettAcctHistory.loading);

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
      <h2 id="fim-sett-acct-history-heading" data-cy="FimSettAcctHistoryHeading">
        <Translate contentKey="fimApp.fimFimSettAcctHistory.home.title">Fim Sett Acct Histories</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="fimApp.fimFimSettAcctHistory.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/fim/fim-sett-acct-history/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="fimApp.fimFimSettAcctHistory.home.createLabel">Create new Fim Sett Acct History</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {fimSettAcctHistoryList && fimSettAcctHistoryList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="fimApp.fimFimSettAcctHistory.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('settaccId')}>
                  <Translate contentKey="fimApp.fimFimSettAcctHistory.settaccId">Settacc Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('settaccId')} />
                </th>
                <th className="hand" onClick={sort('historyTs')}>
                  <Translate contentKey="fimApp.fimFimSettAcctHistory.historyTs">History Ts</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('historyTs')} />
                </th>
                <th className="hand" onClick={sort('accountId')}>
                  <Translate contentKey="fimApp.fimFimSettAcctHistory.accountId">Account Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('accountId')} />
                </th>
                <th className="hand" onClick={sort('settAcctNbr')}>
                  <Translate contentKey="fimApp.fimFimSettAcctHistory.settAcctNbr">Sett Acct Nbr</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('settAcctNbr')} />
                </th>
                <th className="hand" onClick={sort('settCcy')}>
                  <Translate contentKey="fimApp.fimFimSettAcctHistory.settCcy">Sett Ccy</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('settCcy')} />
                </th>
                <th className="hand" onClick={sort('settAcctStatus')}>
                  <Translate contentKey="fimApp.fimFimSettAcctHistory.settAcctStatus">Sett Acct Status</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('settAcctStatus')} />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="fimApp.fimFimSettAcctHistory.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                </th>
                <th className="hand" onClick={sort('createdTs')}>
                  <Translate contentKey="fimApp.fimFimSettAcctHistory.createdTs">Created Ts</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdTs')} />
                </th>
                <th className="hand" onClick={sort('updatedBy')}>
                  <Translate contentKey="fimApp.fimFimSettAcctHistory.updatedBy">Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                </th>
                <th className="hand" onClick={sort('updatedTs')}>
                  <Translate contentKey="fimApp.fimFimSettAcctHistory.updatedTs">Updated Ts</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedTs')} />
                </th>
                <th className="hand" onClick={sort('recordStatus')}>
                  <Translate contentKey="fimApp.fimFimSettAcctHistory.recordStatus">Record Status</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('recordStatus')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {fimSettAcctHistoryList.map((fimSettAcctHistory, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/fim/fim-sett-acct-history/${fimSettAcctHistory.id}`} color="link" size="sm">
                      {fimSettAcctHistory.id}
                    </Button>
                  </td>
                  <td>{fimSettAcctHistory.settaccId}</td>
                  <td>
                    {fimSettAcctHistory.historyTs ? (
                      <TextFormat type="date" value={fimSettAcctHistory.historyTs} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{fimSettAcctHistory.accountId}</td>
                  <td>{fimSettAcctHistory.settAcctNbr}</td>
                  <td>{fimSettAcctHistory.settCcy}</td>
                  <td>{fimSettAcctHistory.settAcctStatus}</td>
                  <td>{fimSettAcctHistory.createdBy}</td>
                  <td>
                    {fimSettAcctHistory.createdTs ? (
                      <TextFormat type="date" value={fimSettAcctHistory.createdTs} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{fimSettAcctHistory.updatedBy}</td>
                  <td>
                    {fimSettAcctHistory.updatedTs ? (
                      <TextFormat type="date" value={fimSettAcctHistory.updatedTs} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{fimSettAcctHistory.recordStatus}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/fim/fim-sett-acct-history/${fimSettAcctHistory.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/fim/fim-sett-acct-history/${fimSettAcctHistory.id}/edit`}
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
                        onClick={() => (window.location.href = `/fim/fim-sett-acct-history/${fimSettAcctHistory.id}/delete`)}
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
              <Translate contentKey="fimApp.fimFimSettAcctHistory.home.notFound">No Fim Sett Acct Histories found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default FimSettAcctHistory;
