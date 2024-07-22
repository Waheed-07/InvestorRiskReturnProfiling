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

import { getEntities } from './fim-sett-acct-wq.reducer';

export const FimSettAcctWq = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const fimSettAcctWqList = useAppSelector(state => state.fim.fimSettAcctWq.entities);
  const loading = useAppSelector(state => state.fim.fimSettAcctWq.loading);

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
      <h2 id="fim-sett-acct-wq-heading" data-cy="FimSettAcctWqHeading">
        <Translate contentKey="fimApp.fimFimSettAcctWq.home.title">Fim Sett Acct Wqs</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="fimApp.fimFimSettAcctWq.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/fim/fim-sett-acct-wq/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="fimApp.fimFimSettAcctWq.home.createLabel">Create new Fim Sett Acct Wq</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {fimSettAcctWqList && fimSettAcctWqList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="fimApp.fimFimSettAcctWq.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('settaccId')}>
                  <Translate contentKey="fimApp.fimFimSettAcctWq.settaccId">Settacc Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('settaccId')} />
                </th>
                <th className="hand" onClick={sort('accountId')}>
                  <Translate contentKey="fimApp.fimFimSettAcctWq.accountId">Account Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('accountId')} />
                </th>
                <th className="hand" onClick={sort('settAcctNbr')}>
                  <Translate contentKey="fimApp.fimFimSettAcctWq.settAcctNbr">Sett Acct Nbr</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('settAcctNbr')} />
                </th>
                <th className="hand" onClick={sort('settCcy')}>
                  <Translate contentKey="fimApp.fimFimSettAcctWq.settCcy">Sett Ccy</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('settCcy')} />
                </th>
                <th className="hand" onClick={sort('settAcctStatus')}>
                  <Translate contentKey="fimApp.fimFimSettAcctWq.settAcctStatus">Sett Acct Status</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('settAcctStatus')} />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="fimApp.fimFimSettAcctWq.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                </th>
                <th className="hand" onClick={sort('createdTs')}>
                  <Translate contentKey="fimApp.fimFimSettAcctWq.createdTs">Created Ts</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdTs')} />
                </th>
                <th className="hand" onClick={sort('updatedBy')}>
                  <Translate contentKey="fimApp.fimFimSettAcctWq.updatedBy">Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                </th>
                <th className="hand" onClick={sort('updatedTs')}>
                  <Translate contentKey="fimApp.fimFimSettAcctWq.updatedTs">Updated Ts</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedTs')} />
                </th>
                <th className="hand" onClick={sort('recordStatus')}>
                  <Translate contentKey="fimApp.fimFimSettAcctWq.recordStatus">Record Status</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('recordStatus')} />
                </th>
                <th className="hand" onClick={sort('uploadRemark')}>
                  <Translate contentKey="fimApp.fimFimSettAcctWq.uploadRemark">Upload Remark</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('uploadRemark')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {fimSettAcctWqList.map((fimSettAcctWq, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/fim/fim-sett-acct-wq/${fimSettAcctWq.id}`} color="link" size="sm">
                      {fimSettAcctWq.id}
                    </Button>
                  </td>
                  <td>{fimSettAcctWq.settaccId}</td>
                  <td>{fimSettAcctWq.accountId}</td>
                  <td>{fimSettAcctWq.settAcctNbr}</td>
                  <td>{fimSettAcctWq.settCcy}</td>
                  <td>{fimSettAcctWq.settAcctStatus}</td>
                  <td>{fimSettAcctWq.createdBy}</td>
                  <td>
                    {fimSettAcctWq.createdTs ? <TextFormat type="date" value={fimSettAcctWq.createdTs} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{fimSettAcctWq.updatedBy}</td>
                  <td>
                    {fimSettAcctWq.updatedTs ? <TextFormat type="date" value={fimSettAcctWq.updatedTs} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{fimSettAcctWq.recordStatus}</td>
                  <td>{fimSettAcctWq.uploadRemark}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/fim/fim-sett-acct-wq/${fimSettAcctWq.id}`}
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
                        to={`/fim/fim-sett-acct-wq/${fimSettAcctWq.id}/edit`}
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
                        onClick={() => (window.location.href = `/fim/fim-sett-acct-wq/${fimSettAcctWq.id}/delete`)}
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
              <Translate contentKey="fimApp.fimFimSettAcctWq.home.notFound">No Fim Sett Acct Wqs found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default FimSettAcctWq;
